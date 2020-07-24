package br.com.healthswar.player.view;

import br.com.anonymous.frontend.Button;
import br.com.anonymous.frontend.Panel;
import br.com.anonymous.frontend.TextField;
import br.com.healthswar.gameplay.CardLocal;
import br.com.healthswar.gameplay.FighterField;
import br.com.healthswar.gameplay.fighters.Fighter;
import br.com.healthswar.utils.ColorsUtil;
import br.com.healthswar.utils.Fonts;
import br.com.healthswar.utils.StringUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;

public class FighterDialog extends JDialog {

    private Panel container;
    private TextField title;
    private Fighter[] fighters;
    private Button x;

    public FighterDialog() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(750, 250);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setModal(true);
        fighters = new Fighter[5];

        init();

        setContentPane(container);
        setVisible(true);
    }

    private void init() {
        Border lineBorder = BorderFactory.createLineBorder(ColorsUtil.LETTERS_COLOR, 3);
        container = new Panel(ColorsUtil.BACKGROUND_COLOR);
        container.setBorder(lineBorder);

        title = new TextField(getWidth(), 50,
                StringUtil.CHOOSE_FIGHTER, Fonts.INPUT,
                ColorsUtil.LETTERS_COLOR, ColorsUtil.BACKGROUND_COLOR, false);
        title.setLocation(0, 0);

        x = new Button(50, 50, null,
                ColorsUtil.BACKGROUND_COLOR, Fonts.INPUT, StringUtil.X, null,
                0, null, ColorsUtil.BACKGROUND_COLOR);
        x.setLocation(getWidth() - x.getWidth(), 0);
        x.addActionListener(close());
        x.setOpaque(false);

        container.add(x);
        container.add(title);
    }

    private ActionListener close() {
        return e -> dispose();
    }

    public void setSelector(FighterField[] fightersFields) {
        removeAll();

        int x = 20;
        int y = 40;

        for(int i = 0; i < 5; i++) {
            fighters[i] = fightersFields[i].getFighter();

            if(fighters[i] != null) {
                fighters[i].setLocation(x, y);
                fighters[i].local = CardLocal.SELECTOR;
                container.add(fighters[i]);
                System.out.println(fighters[i].getName());
                title.setText(fighters[i].getName());
            }

            x += 120;
        }
    }

}
