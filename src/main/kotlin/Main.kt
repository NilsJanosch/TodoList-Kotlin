package xyz.truthy.dev

import java.awt.*
import java.awt.event.ActionEvent
import javax.swing.*
import javax.swing.border.EmptyBorder

fun main() {
    SwingUtilities.invokeLater {
        createAndShowGUI()
    }
}

fun createAndShowGUI() {
    val frame = JFrame("To-Do List")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.minimumSize = Dimension(400, 300)

    // Dark Mode Colors
    val bgColor = Color(45, 45, 45)
    val fgColor = Color(200, 200, 200)
    val buttonColor = Color(70, 70, 70)
    val borderColor = Color(100, 100, 100)

    // Main Panel
    val panel = JPanel()
    panel.layout = BorderLayout(10, 10)
    panel.background = bgColor
    panel.border = EmptyBorder(10, 10, 10, 10)

    // Input Field and Button
    val textField = JTextField()
    textField.background = buttonColor
    textField.foreground = fgColor
    textField.border = BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(borderColor, 1, true),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
    )

    val addButton = JButton("Add To-Do")
    addButton.background = buttonColor
    addButton.foreground = fgColor
    addButton.border = BorderFactory.createLineBorder(borderColor, 1, true)

    val inputPanel = JPanel()
    inputPanel.layout = BoxLayout(inputPanel, BoxLayout.X_AXIS)
    inputPanel.background = bgColor
    inputPanel.add(textField)
    inputPanel.add(addButton)

    // List Model and List Component
    val listModel = DefaultListModel<String>()
    val list = JList(listModel)
    list.background = bgColor
    list.foreground = fgColor
    list.selectionBackground = Color(100, 100, 100)
    list.selectionForeground = fgColor

    val scrollPane = JScrollPane(list)
    scrollPane.border = BorderFactory.createLineBorder(borderColor, 1, true)
    scrollPane.background = bgColor

    // Button to remove selected item
    val deleteButton = JButton("Delete Selected")
    deleteButton.background = buttonColor
    deleteButton.foreground = fgColor
    deleteButton.border = BorderFactory.createLineBorder(borderColor, 1, true)

    val completeButton = JButton("Mark as Completed")
    completeButton.background = buttonColor
    completeButton.foreground = fgColor
    completeButton.border = BorderFactory.createLineBorder(borderColor, 1, true)

    val controlPanel = JPanel()
    controlPanel.layout = BoxLayout(controlPanel, BoxLayout.X_AXIS)
    controlPanel.background = bgColor
    controlPanel.add(deleteButton)
    controlPanel.add(completeButton)

    panel.add(inputPanel, BorderLayout.NORTH)
    panel.add(scrollPane, BorderLayout.CENTER)
    panel.add(controlPanel, BorderLayout.SOUTH)

    // Action Listeners
    addButton.addActionListener { e: ActionEvent ->
        val text = textField.text
        if (text.isNotBlank()) {
            listModel.addElement(text)
            textField.text = ""
        }
    }

    deleteButton.addActionListener {
        val selectedIndex = list.selectedIndex
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex)
        }
    }

    completeButton.addActionListener {
        val selectedIndex = list.selectedIndex
        if (selectedIndex != -1) {
            val selectedItem = listModel.getElementAt(selectedIndex)
            listModel.set(selectedIndex, "✓ $selectedItem")
        }
    }

    frame.contentPane.add(panel)
    frame.pack()
    frame.isVisible = true
}
