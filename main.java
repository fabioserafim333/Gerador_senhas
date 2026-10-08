import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;

public class Main extends JFrame {

    private JTextField campoSenha;
    private JSpinner tamanho;
    private JCheckBox maiusculas;
    private JCheckBox minusculas;
    private JCheckBox numeros;
    private JCheckBox simbolos;

    private final SecureRandom random = new SecureRandom();

    private final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private final String NUMEROS = "0123456789";
    private final String SIMBOLOS = "!@#$%&*+-=?";

    public Main() {

        setTitle("Gerador de Senhas");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Gerador de Senhas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo da senha
        campoSenha = new JTextField();
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));
        campoSenha.setHorizontalAlignment(JTextField.CENTER);
        campoSenha.setEditable(false);

        painel.add(campoSenha);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Tamanho
        JPanel painelTamanho = new JPanel();

        JLabel labelTamanho = new JLabel("Tamanho:");

        tamanho = new JSpinner(new SpinnerNumberModel(12, 4, 50, 1));

        painelTamanho.add(labelTamanho);
        painelTamanho.add(tamanho);

        painel.add(painelTamanho);

        // Opções
        maiusculas = new JCheckBox("Letras maiúsculas", true);
        minusculas = new JCheckBox("Letras minúsculas", true);
        numeros = new JCheckBox("Números", true);
        simbolos = new JCheckBox("Símbolos", true);

        painel.add(maiusculas);
        painel.add(minusculas);
        painel.add(numeros);
        painel.add(simbolos);

        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Botões
        JPanel painelBotoes = new JPanel();

        JButton gerar = new JButton("Gerar senha");
        JButton copiar = new JButton("Copiar");

        painelBotoes.add(gerar);
        painelBotoes.add(copiar);

        painel.add(painelBotoes);

        // Evento do botão gerar
        gerar.addActionListener(e -> gerarSenha());

        // Evento do botão copiar
        copiar.addActionListener(e -> copiarSenha());

        add(painel);

        setVisible(true);
    }

    private void gerarSenha() {

        String caracteres = "";

        if (maiusculas.isSelected()) {
            caracteres += LETRAS_MAIUSCULAS;
        }

        if (minusculas.isSelected()) {
            caracteres += LETRAS_MINUSCULAS;
        }

        if (numeros.isSelected()) {
            caracteres += NUMEROS;
        }

        if (simbolos.isSelected()) {
            caracteres += SIMBOLOS;
        }

        if (caracteres.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione pelo menos um tipo de caractere."
            );
            return;
        }

        int tamanhoSenha = (int) tamanho.getValue();

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < tamanhoSenha; i++) {
            int indice = random.nextInt(caracteres.length());
            senha.append(caracteres.charAt(indice));
        }

        campoSenha.setText(senha.toString());
    }

    private void copiarSenha() {

        String senha = campoSenha.getText();

        if (senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Gere uma senha primeiro."
            );
            return;
        }

        StringSelection selecao = new StringSelection(senha);

        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selecao, null);

        JOptionPane.showMessageDialog(
            this,
            "Senha copiada!"
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
