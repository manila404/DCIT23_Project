package tetris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class GameForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private GameArea ga;
    private GameThread gt;
    private JButton btnToggleSettings; // Button for toggling settings menu
    private JPopupMenu settingsMenu; // Settings menu
    private JMenuItem toggleMusicItem; // Menu item for toggling music
    private JMenuItem nightModeItem;
    private boolean isNightModeEnabled = false;

    public GameForm() {
        initComponents();

        ga = new GameArea(gameAreaPlaceholder, 10);
        this.add(ga);

        initControls();
        initSettingsMenu();
    }

    private void initComponents() {

        gameAreaPlaceholder = new javax.swing.JPanel();
        scoreDisplay = new javax.swing.JLabel();
        levelDisplay = new javax.swing.JLabel();
        btnMainMenu = new javax.swing.JButton();
        btnToggleSettings = new javax.swing.JButton(); // Added button for toggling settings menu

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameAreaPlaceholder.setBackground(new java.awt.Color(238, 238, 238));
        gameAreaPlaceholder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout gameAreaPlaceholderLayout = new javax.swing.GroupLayout(gameAreaPlaceholder);
        gameAreaPlaceholder.setLayout(gameAreaPlaceholderLayout);
        gameAreaPlaceholderLayout.setHorizontalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        gameAreaPlaceholderLayout.setVerticalGroup(
            gameAreaPlaceholderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        scoreDisplay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        scoreDisplay.setText("Score: 0");

        levelDisplay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        levelDisplay.setText("Level: 1");

        btnMainMenu.setText("Main Menu");
        btnMainMenu.setFocusable(false);
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        btnToggleSettings.setText("Settings"); // Set button text to "Settings"
        btnToggleSettings.setFocusable(false);
        btnToggleSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleSettingsActionPerformed(evt);
            }
        });

        JLabel lblMoveLeft = new JLabel("← - Move Left");
        JLabel lblMoveRight = new JLabel("→ - Move Right");
        JLabel lblRotateClockwise = new JLabel("↑ - Rotate Clockwise");
        JLabel lblDrop = new JLabel("Space - Drop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(levelDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnToggleSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMoveLeft)
                    .addComponent(lblMoveRight)
                    .addComponent(lblRotateClockwise)
                    .addComponent(lblDrop))
                .addGap(62, 62, 62))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMainMenu)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scoreDisplay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(levelDisplay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnToggleSettings) // Added button to layout
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoveLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoveRight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRotateClockwise)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDrop))
                    .addComponent(gameAreaPlaceholder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
    }

    private void initControls() {
        InputMap im = this.getRootPane().getInputMap();
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        im.put(KeyStroke.getKeyStroke("SPACE"), "drop");

        am.put("right", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });

        am.put("left", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });

        am.put("up", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });

        am.put("down", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockDown();
            }
        });

        am.put("drop", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }
        });
    }


    private void initSettingsMenu() {
        settingsMenu = new JPopupMenu();

        toggleMusicItem = new JMenuItem("Off Music");
        toggleMusicItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
            }
        });
        settingsMenu.add(toggleMusicItem);

        nightModeItem = new JMenuItem("Night Mode");
        nightModeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleNightModeActionPerformed(evt);
            }
        });
        settingsMenu.add(nightModeItem);

    }

    public void startGame() {
        ga.initBackgroundArray();
        gt = new GameThread(ga, this);
        gt.start();

        if (isNightModeEnabled) {
            gameAreaPlaceholder.setBackground(Color.BLACK); // Set the initial background to black
        } else {
            gameAreaPlaceholder.setBackground(new Color(238, 238, 238)); // Set the initial background to default color
        }
    }

    public void updateScore(int score) {
        scoreDisplay.setText("Score: " + score);
    }

    public void updateLevel(int level) {
        levelDisplay.setText("Level: " + level);
    }

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a new popup menu for the main menu options
        JPopupMenu mainMenu = new JPopupMenu();

        // Create a menu item for quitting the game
        JMenuItem quitItem = new JMenuItem("Quit Game");

        // Add an action listener to the quit menu item
        quitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Handle Quit Game menu item click event
                // Add your code here to perform the necessary actions for quitting the game
            }
        });

        // Add the quit menu item to the popup menu
        mainMenu.add(quitItem);

        // Show the popup menu at the button's location
        mainMenu.show(btnMainMenu, 0, btnMainMenu.getHeight());
    }



    private void btnToggleSettingsActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Settings button click event
        settingsMenu.show(btnToggleSettings, 0, btnToggleSettings.getHeight());
    }

    private void toggleNightModeActionPerformed(java.awt.event.ActionEvent evt) {
        isNightModeEnabled = !isNightModeEnabled; // Toggle the night mode flag

        if (isNightModeEnabled) {
            getContentPane().setBackground(Color.BLACK); // Set the window's background to black
            nightModeItem.setText("Day Mode"); // Update the menu item text
            setTextColor(Color.WHITE); // Set text color to white
        } else {
            getContentPane().setBackground(new Color(238, 238, 238)); // Set the window's background to default color
            nightModeItem.setText("Night Mode"); // Update the menu item text
            setTextColor(Color.BLACK); // Set text color to black
        }
    }

    private void setTextColor(Color color) {
        scoreDisplay.setForeground(color);
        levelDisplay.setForeground(color);
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameForm().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnMainMenu;
    private javax.swing.JPanel gameAreaPlaceholder;
    private javax.swing.JLabel levelDisplay;
    private javax.swing.JLabel scoreDisplay;
}
