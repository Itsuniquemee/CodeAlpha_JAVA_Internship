import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Chatbot {
    private static final Map<String, String> faq = new HashMap<>();

    static {
        faq.put("hello", "Hello! How can I help you today?");
        faq.put("hi", "Hi there! What can I do for you?");
        faq.put("how are you", "I'm just a bot, but I'm here to help!");
        faq.put("what is your name", "I'm your Java Chatbot.");
        faq.put("bye", "Goodbye! Have a great day.");
        faq.put("help", "You can ask me about our services, hours, or any general question.");
        faq.put("services", "We offer student grade tracking, chatbots, and more.");
        faq.put("hours", "We are available 24/7 online.");
    }

    private static String getResponse(String input) {
        String normalized = input.trim().toLowerCase();
        for (Map.Entry<String, String> entry : faq.entrySet()) {
            if (normalized.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Sorry, I don't understand. Can you rephrase?";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Chatbot");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new BorderLayout());

            JTextArea chatArea = new JTextArea();
            chatArea.setEditable(false);
            chatArea.setLineWrap(true);
            chatArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(chatArea);

            JTextField inputField = new JTextField();
            JButton sendButton = new JButton("Send");

            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(inputField, BorderLayout.CENTER);
            inputPanel.add(sendButton, BorderLayout.EAST);

            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(inputPanel, BorderLayout.SOUTH);

            sendButton.addActionListener(_ -> {
                String userInput = inputField.getText();
                if (!userInput.isEmpty()) {
                    chatArea.append("You: " + userInput + System.lineSeparator());
                    String response = getResponse(userInput);
                    chatArea.append("Bot: " + response + System.lineSeparator());
                    inputField.setText("");
                }
            });
            inputField.addActionListener(_ -> sendButton.doClick());

            frame.setVisible(true);
        });
    }
}
