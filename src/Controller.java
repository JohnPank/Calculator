import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

/**
 * Created by JohnPank on 18.07.17
 */
public class Controller {

    double a = 0;
    double b = 0;
    double mem = 0;

    boolean useFunc = false;
    int func = 0; //1--,2-+,3-*,4-/

    @FXML
    TextField lcd;

    @FXML
    Label memInd;

    @FXML
    Button bDiv, bSum, bRaz, bMul, bAns, bMpl, bMmin, bClr, bRM, bDot, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;

    public void initialize(){
        lcd.setText("0");

        b0.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(0));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(1));
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(2));
        b3.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(3));
        b4.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(4));
        b5.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(5));
        b6.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(6));
        b7.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(7));
        b8.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(8));
        b9.addEventHandler(MouseEvent.MOUSE_CLICKED, new AddDigit(9));

        //обработка кнопки Mem+
        bMpl.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mem += Double.parseDouble(lcd.getText());
                if(mem != 0)
                    memInd.setVisible(true);
                else
                    memInd.setVisible(false);
                useFunc = true;
            }
        });

        //обработка кнопки Mem-
        bMmin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mem -= Double.parseDouble(lcd.getText());
                if(mem != 0)
                    memInd.setVisible(true);
                else
                    memInd.setVisible(false);
                useFunc = true;
            }
        });

        //обработка кнопки RM
        bRM.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lcd.setText(String.valueOf(mem));
                mem = 0;
                memInd.setVisible(false);
                useFunc = true;
            }
        });


        //обработка кнопки Clear
        bClr.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lcd.setText("0");
                a = 0;
                b = 0;
                useFunc = false;
                func = 0;
            }
        });

        //обработка кнопки Dot
        bDot.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if((Double.parseDouble(lcd.getText())%1) == 0){
                    if(Objects.equals(lcd.getText(), "0")){
                        lcd.setText("0.");
                    }
                    else {
                        lcd.setText(lcd.getText() + ".");
                    }
                }
            }
        });

        //обработка кнопки Razn
        bRaz.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useFunc = true;
                a=Double.parseDouble(lcd.getText());
                func = 1;
            }
        });

        //обработка кнопки Sum
        bSum.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useFunc = true;
                a=Double.parseDouble(lcd.getText());
                func = 2;
            }
        });

        //обработка кнопки Mul
        bMul.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useFunc = true;
                a=Double.parseDouble(lcd.getText());
                func = 3;
            }
        });

        //обработка кнопки Div
        bDiv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useFunc = true;
                a=Double.parseDouble(lcd.getText());
                func = 4;
            }
        });

        //обработка кнопки Ans
        bAns.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useFunc = true;
                calculate();
                func = 0;
            }
        });
    }

    private void calculate(){
        switch (func){
            case 1: showAnsver(a - Double.parseDouble(lcd.getText()));break;
            case 2: showAnsver(a + Double.parseDouble(lcd.getText()));break;
            case 3: showAnsver(a * Double.parseDouble(lcd.getText()));break;
            case 4: showAnsver(a / Double.parseDouble(lcd.getText()));break;
            default:break;
        }
    }

    private void showAnsver(double ans){
        if(ans%1 != 0)
            lcd.setText(String.valueOf(ans));
        else
            lcd.setText(String.valueOf((int)ans));

    }

    //добавление цифры к числу на зкране
    class AddDigit implements EventHandler<MouseEvent>{
        int butId;
        AddDigit(int id){
            butId = id;
        }

        @Override
        public void handle(MouseEvent event) {
            if(!useFunc){
                if(Objects.equals(lcd.getText(), "0"))
                    lcd.setText(String.valueOf(butId));
                else
                if(lcd.getText().length() <= 9)
                    lcd.setText(lcd.getText() + String.valueOf(butId));
            }
            else {
                lcd.setText(String.valueOf(butId));
                useFunc = false;
            }
        }
    }
}
