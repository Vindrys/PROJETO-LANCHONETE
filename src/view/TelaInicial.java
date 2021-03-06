package view;

import com.sun.xml.internal.ws.handler.HandlerException;
import control.Gerencia;
import control.GerenciaUsuario;
import model.Funcionario;
import model.Pessoa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;

public class TelaInicial extends JDialog {
    
    private static Funcionario usuario;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton cadastrarButton;
    private JPasswordField passwordField1;
    private JLabel LogoInicial;
    private JTextField textField1;
    private JButton loginButton;

    public TelaInicial() {

        setContentPane(contentPane);
        setLocation(400,80);
        setTitle("Bem Vindo !");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(450,520);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("images/icon1.jpg").getImage());
        setBackground(Color.WHITE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField1.getText();
                String senha = new String(passwordField1.getPassword());

                try {
                    if (GerenciaUsuario.Autenticar(email, senha)) {
                        usuario = GerenciaUsuario.buscarUsuario(textField1.getText());
                        TelaPrincipal principal = new TelaPrincipal();
                        principal.pack();
                        dispose();
                        principal.setVisible(true);
                        System.exit(0);

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Nao foi Possivel Autenticar",
                                "Mensagem de erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (HeadlessException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch(IOException e1){
                    JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro();
                cadastro.pack();
                cadastro.setVisible(true);
            }
        });

    }


    private void createUIComponents() {
        LogoInicial = new JLabel();
        ImageIcon icon = new ImageIcon("images/logo.png");
        LogoInicial.setIcon(icon);
    }

    public static void main(String[] args) {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.pack();
        telaInicial.setVisible(true);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }
    public static Funcionario retornaUsuario(){
        return usuario;
    }
    public static void setarUsuario(Funcionario func){
        usuario = func;
    }

}
