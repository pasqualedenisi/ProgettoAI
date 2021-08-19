package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.sasl.SaslClient;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import constants.Screens;

public class StartMenuPanel extends JPanel {
	
	public StartMenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JButton playButton = new JButton();
		JButton quitButton = new JButton();

		ImageIcon icon = new ImageIcon(Screens.ICONS_PATH+Screens.PLAY_BUTTON);
		icon = resizeIcon(icon);
		configureButton(playButton, icon);
		icon = new ImageIcon(Screens.ICONS_PATH+Screens.QUIT_BUTTON);
		icon = resizeIcon(icon);
		configureButton(quitButton, icon);
		
		add(Box.createVerticalGlue());
		add(playButton);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(quitButton);
		add(Box.createVerticalGlue());
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenManager screenContainer = ScreenManager.getInstance();
				CardLayout c = (CardLayout)screenContainer.getCards();
				c.show(screenContainer, Screens.LEVELS_OVERVIEW);
			}
		});
	}

	private void configureButton(JButton button, ImageIcon icon) {
		button.setIcon(icon);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
	}

	private ImageIcon resizeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		img = img.getScaledInstance(150, 85, Image.SCALE_SMOOTH);
		icon.setImage(img);
		return icon;
	}
}
