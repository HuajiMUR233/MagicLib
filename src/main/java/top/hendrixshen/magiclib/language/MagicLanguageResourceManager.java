package top.hendrixshen.magiclib.language;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleResource;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

//#if MC <= 11404
//$$ import net.minecraft.server.packs.Pack;
//#endif

public class MagicLanguageResourceManager implements ResourceManager {

    private final Set<String> namespaces = new HashSet<>();
    private final HashMap<ResourceLocation, Set<Resource>> resources = new HashMap<>();

    private static final Pattern languageResourcePattern = Pattern.compile("^assets/([\\w-]*)/lang/([\\w-]*)\\.json$");

    private void addResources(Set<Resource> resources) {
        for (Resource resource : resources) {
            ResourceLocation location = resource.getLocation();
            namespaces.add(location.getNamespace());
            Set<Resource> resourceList = this.resources.computeIfAbsent(location, resourceLocation -> new HashSet<>());
            resourceList.add(resource);
        }
    }

    public MagicLanguageResourceManager() {

        try {
            for (URL resource : Collections.list(this.getClass().getClassLoader().getResources("assets"))) {
                if (resource.getProtocol().equalsIgnoreCase("jar")) {
                    addResources(getJarResources(resource));
                } else if (resource.getProtocol().equalsIgnoreCase("file")) {
                    addResources(getFileResources(resource));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Set<Resource> getJarResources(URL resourceUrl) throws IOException {
        JarURLConnection conn = (JarURLConnection) resourceUrl.openConnection();
        JarFile jar = conn.getJarFile();
        Set<Resource> ret = new HashSet<>();

        for (JarEntry entry : Collections.list(jar.entries())) {
            String entryName = entry.getName();
            Matcher matcher = languageResourcePattern.matcher(entryName);
            if (matcher.find()) {
                String namespace = matcher.group(1);
                String code = matcher.group(2);
                if (!entry.isDirectory()) {
                    InputStream input = jar.getInputStream(entry);
                    String languagePath = String.format("lang/%s.json", code);
                    Resource resource = new SimpleResource(namespace, new ResourceLocation(namespace, languagePath), input, null);
                    ret.add(resource);
                }
            }
        }
        return ret;
    }

    private static Set<Resource> getFileResources(URL resourceUrl) throws IOException {
        Set<Resource> ret = new HashSet<>();
        try {
            Path assetsPath = Paths.get(resourceUrl.toURI());
            Files.walkFileTree(assetsPath, new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = "assets/" + assetsPath.relativize(file).toString().replace("\\", "/");
                    Matcher matcher = languageResourcePattern.matcher(fileName);
                    if (matcher.find()) {
                        String namespace = matcher.group(1);
                        String code = matcher.group(2);
                        InputStream input = Files.newInputStream(file);
                        String languagePath = String.format("lang/%s.json", code);
                        Resource resource = new SimpleResource(namespace, new ResourceLocation(namespace, languagePath),
                                input, null);
                        ret.add(resource);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }


    @Override
    public Set<String> getNamespaces() {
        return namespaces;
    }

    @Override
    public boolean hasResource(@NotNull ResourceLocation resourceLocation) {
        return false;
    }

    @Override
    public List<Resource> getResources(@NotNull ResourceLocation resourceLocation) {
        return new ArrayList<>(resources.getOrDefault(resourceLocation, new HashSet<>()));
    }

    @Override
    public Collection<ResourceLocation> listResources(@NotNull String string, @NotNull Predicate<String> predicate) {
        return null;
    }

    //#if MC > 11502
    @Override
    public Stream<PackResources> listPacks() {
        return null;
    }
    //#endif

    //#if MC <= 11404
    //$$ @Override
    //$$ public void add(Pack pack) {}
    //#endif

    @Override
    public Resource getResource(@NotNull ResourceLocation resourceLocation) {
        return null;
    }
}