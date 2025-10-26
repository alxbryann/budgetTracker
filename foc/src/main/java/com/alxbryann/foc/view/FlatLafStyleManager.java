package com.alxbryann.foc.view;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;
import javax.swing.JComponent;

public class FlatLafStyleManager {
    
    // Estilos para botones primarios (Income)
    public static void applyPrimaryButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Component.accentColor;"
                + "foreground:$Component.accentForeground;"
                + "borderColor:$Component.accentColor;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken($Component.accentColor,10%);"
                + "pressedBackground:darken($Component.accentColor,20%);"
                + "borderWidth:1;"
                + "arc:15");
    }
    
    // Estilos para botones secundarios (Expense)
    public static void applySecondaryButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Component.secondaryBackground;"
                + "foreground:$Component.secondaryForeground;"
                + "borderColor:$Component.borderColor;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken($Component.secondaryBackground,10%);"
                + "pressedBackground:darken($Component.secondaryBackground,20%);"
                + "borderWidth:1;"
                + "arc:15");
    }
    
    // Estilos para botones de success/confirmación
    public static void applySuccessButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#90CBAD;"
                + "foreground:#ffffff;"
                + "borderColor:#90CBAD;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken(#90CBAD,10%);"
                + "pressedBackground:darken(#90CBAD,20%);"
                + "borderWidth:1;"
                + "arc:15");
    }
    
    // Estilos para botones de danger/error
    public static void applyDangerButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#D28585;"
                + "foreground:#ffffff;"
                + "borderColor:#D28585;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken(#D28585,10%);"
                + "pressedBackground:darken(#D28585,20%);"
                + "borderWidth:1;"
                + "arc:15");
    }
    
    // Estilos para botones de modal (Close, Update, etc.)
    public static void applyModalButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:#563C10;"
                + "foreground:#ffffff;"
                + "borderColor:#563C10;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken(#563C10,10%);"
                + "pressedBackground:darken(#563C10,20%);"
                + "borderWidth:1;"
                + "arc:15");
    }
    
    // Estilos para botones de confirmación en diálogos
    public static void applyConfirmButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Actions.Green;"
                + "foreground:#ffffff;"
                + "borderColor:$Actions.Green;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken($Actions.Green,10%);"
                + "pressedBackground:darken($Actions.Green,20%);"
                + "borderWidth:1;"
                + "arc:10");
    }
    
    // Estilos para botones de cancelación en diálogos
    public static void applyCancelButtonStyle(JButton button) {
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Actions.Red;"
                + "foreground:#ffffff;"
                + "borderColor:$Actions.Red;"
                + "focusColor:$Component.focusColor;"
                + "hoverBackground:darken($Actions.Red,10%);"
                + "pressedBackground:darken($Actions.Red,20%);"
                + "borderWidth:1;"
                + "arc:10");
    }
    
    // Método utilitario para aplicar estilos generales a componentes
    public static void applyRoundedBorder(JComponent component, int arc) {
        component.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:" + arc);
    }
}
