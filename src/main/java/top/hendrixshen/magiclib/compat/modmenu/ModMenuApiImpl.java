package top.hendrixshen.magiclib.compat.modmenu;
//#if MC >= 11605
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
//#endif
import top.hendrixshen.magiclib.MagicLibConfigGui;

//#if MC <= 11502
//$$ import io.github.prospector.modmenu.api.ConfigScreenFactory;
//$$ import io.github.prospector.modmenu.api.ModMenuApi;
//#endif

//#if MC <= 11404
//$$ import top.hendrixshen.magiclib.MagicLibReference;
//#endif


public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> {
            MagicLibConfigGui gui = MagicLibConfigGui.getInstance();
            gui.setParent(screen);
            return gui;
        };
    }

    //#if MC <= 11404
    //$$ @Override
    //$$ public String getModId() {
    //$$     return MagicLibReference.getModId();
    //$$ }
    //#endif
}