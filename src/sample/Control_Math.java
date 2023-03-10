package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
//import org.scilab.forge.jlatexmath.TeXConstants;
//import org.scilab.forge.jlatexmath.TeXFormula;

public class Control_Math {
//    @FXML private WebView mathWebView = new WebView();

    boolean arc = false;
    boolean cap = false;
    boolean sub = false;
    boolean sup = false;

//    String latex = "\\forall\\varepsilon\\in\\mathbb{R}_+^*\\ \\exists\\eta>0\\ |x-x_0|\\leq\\eta\\Longrightarrow|f(x)-f(x_0)|\\leq\\varepsilon\\\\";
    String sign = "";

//    TeXFormula formula = new TeXFormula;

    public void arc(ActionEvent event){
        arc = !arc;
        cap = false;
        sub = false;
        sup = false;
    }
    public void caplock(ActionEvent event){
        cap = !cap;
    }
//    public void loadFormula(ActionEvent event){
//        mathWebView.getEngine().loadContent(latex);
//    }
    public void mathSign(ActionEvent event){
        Button btn = (Button) event.getSource();
        if (sup){
            switch(btn.getText()){
                case "0": sign = "⁰"; break;
                case "1": sign = "¹"; break;
                case "2": sign = "²"; break;
                case "3": sign = "³"; break;
                case "4": sign = "⁴"; break;
                case "5": sign = "⁵"; break;
                case "6": sign = "⁶"; break;
                case "7": sign = "⁷"; break;
                case "8": sign = "⁸"; break;
                case "9": sign = "⁹"; break;
                case "+": sign = "⁺"; break;
                case "-": sign = "⁻"; break;
                case "=": sign = "⁼"; break;
                case "a": if(cap){sign = "ᴬ";} else {sign = "ª";} break;
                case "b": if(cap){sign = "ᴮ";} else {sign = "ᵇ";} break;
                case "c": sign = "ᶜ"; break;
                case "d": if(cap){sign = "ᴰ";} else {sign = "ᵈ";} break;
                case "e": if(cap){sign = "ᴱ";} else {sign = "ᵉ";} break;
                case "f": sign = "ᶠ"; break;
                case "g": if(cap){sign = "ᴳ";} else {sign = "ᵍ";} break;
                case "h": if(cap){sign = "ᴴ";} else {sign = "ʰ";} break;
                case "i": if(cap){sign = "ᴵ";} else {sign = "ⁱ";} break;
                case "j": if(cap){sign = "ᴶ";} else {sign = "ʲ";} break;
                case "k": if(cap){sign = "ᴷ";} else {sign = "ᵏ";} break;
                case "l": if(cap){sign = "ᴸ";} else {sign = "ˡ";} break;
                case "m": if(cap){sign = "ᴹ";} else {sign = "ᵐ";} break;
                case "n": if(cap){sign = "ᴺ";} else {sign = "ⁿ";} break;
                case "o": if(cap){sign = "ᴼ";} else {sign = "º";} break;
                case "p": if(cap){sign = "ᴾ";} else {sign = "ᵖ";} break;
                case "r": if(cap){sign = "ᴿ";} else {sign = "ʳ";} break;
                case "s": sign = "ˢ"; break;
                case "t": if(cap){sign = "ᵀ";} else {sign = "ᵗ";} break;
                case "u": if(cap){sign = "ᵁ";} else {sign = "ᵘ";} break;
                case "v": sign = "ⱽ"; break;
                case "w": if(cap){sign = "ᵂ";} else {sign = "ʷ";} break;
                case "x": sign = "ˣ"; break;
                case "y": sign = "ʸ"; break;
                case "z": sign = "ᶻ"; break;
                case "Æ": sign = "ᴭ"; break;
                case "β": sign = "ᵝ"; break;
                case "γ": sign = "ᵞ"; break;
                case "δ": sign = "ᵟ"; break;
                case "ϕ": sign = "ᵠ"; break;
                case "ə": sign = "ᵊ"; break;
                case "ε": sign = "ᵋ"; break;
                case "Θ": sign = "ᶱ"; break;
                case "θ": sign = "ᶿ"; break;
                case "Λ": sign = "ᶺ"; break;
                case "()": sign = "⁽⁾"; break;
                case "∫": sign = "ᶴ"; break;
                default: sign = ""; System.out.println("This word cannot be supscripted");
            }
        } else if (sub) {
            switch(btn.getText()){
                case "0": sign = "₀"; break;
                case "1": sign = "₁"; break;
                case "2": sign = "₂"; break;
                case "3": sign = "₃"; break;
                case "4": sign = "₄"; break;
                case "5": sign = "₅"; break;
                case "6": sign = "₆"; break;
                case "7": sign = "₇"; break;
                case "8": sign = "₈"; break;
                case "9": sign = "₉"; break;
                case "+": sign = "₊"; break;
                case "-": sign = "₋"; break;
                case "=": sign = "₌"; break;
                case "a": sign = "ₐ"; break;
                case "e": sign = "ₑ"; break;
                case "h": sign = "ₕ"; break;
                case "i": sign = "ᵢ"; break;
                case "j": sign = "ⱼ"; break;
                case "k": sign = "ₖ"; break;
                case "l": sign = "ₗ"; break;
                case "m": sign = "ₘ"; break;
                case "n": sign = "ₙ"; break;
                case "o": sign = "ₒ"; break;
                case "p": sign = "ₚ"; break;
                case "r": sign = "ᵣ"; break;
                case "s": sign = "ₛ"; break;
                case "t": sign = "ₜ"; break;
                case "u": sign = "ᵤ"; break;
                case "v": sign = "ᵥ"; break;
                case "x": sign = "ₓ"; break;
                case "y": sign = "ᵧ"; break;
                case "()": sign = "₍₎"; break;
                case "β": sign = "ᵦ"; break;
                case "γ": sign = "ᵧ"; break;
                case "ϕ": sign = "ᵩ"; break;
                case "ə": sign = "ₔ"; break;
                default: sign = ""; System.out.println("This word cannot be subscripted");
            }
        } else if (cap) {
            switch (btn.getText()){
                case "a": sign = "A"; break;
                case "b": sign = "B"; break;
                case "c": sign = "C"; break;
                case "d": sign = "D"; break;
                case "e": sign = "E"; break;
                case "f": sign = "F"; break;
                case "g": sign = "G"; break;
                case "h": sign = "H"; break;
                case "i": sign = "I"; break;
                case "j": sign = "J"; break;
                case "k": sign = "K"; break;
                case "l": sign = "L"; break;
                case "m": sign = "M"; break;
                case "n": sign = "N"; break;
                case "o": sign = "O"; break;
                case "p": sign = "P"; break;
                case "q": sign = "Q"; break;
                case "r": sign = "R"; break;
                case "s": sign = "S"; break;
                case "t": sign = "T"; break;
                case "u": sign = "U"; break;
                case "v": sign = "V"; break;
                case "w": sign = "W"; break;
                case "x": sign = "X"; break;
                case "y": sign = "Y"; break;
                case "z": sign = "Z"; break;
                default: sign = ""; System.out.println("This word cannot be capital");
            }
        } else if (arc) {
            switch (btn.getText()){
                case "sin": sign = "arcsin"; break;
                case "cos": sign = "arccos"; break;
                case "tan": sign = "arctan"; break;
                case "cot": sign = "arccot"; break;
                case "sec": sign = "arcsec"; break;
                case "csc": sign = "arccsc"; break;
                case "sinh": sign = "arsinh"; break;
                case "cosh": sign = "arcosh"; break;
                case "tanh": sign = "artanh"; break;
                case "coth": sign = "arcoth"; break;
                case "sech": sign = "arsech"; break;
                case "csch": sign = "arcsch"; break;
            }
        } else {
            sign = btn.getText();
        }
        if(!(Subject_Var.lastSelectedTextField == null)){
            int caretPos = Subject_Var.lastSelectedTextField.getCaretPosition();
            Subject_Var.lastSelectedTextField.insertText(caretPos, sign);
            Subject_Var.lastSelectedTextField.requestFocus();
        }
    }
    public void subscript(ActionEvent event){
        arc = false;
        cap = false;
        sub = !sub;
        sup = false;
    }
    public void supscript(ActionEvent event){
        arc = false;
        cap = false;
        sub = false;
        sup = !sup;
    }
}

