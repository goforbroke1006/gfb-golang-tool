package com.gfb.ide.plugin.goland.settings;

import com.gfb.ide.plugin.goland.run.configuration.RunConfiguration;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MainViewSettingsEditor extends SettingsEditor<RunConfiguration> {
    private Project project;
    private TextFieldWithBrowseButton scriptNameTextField;
    private JTextArea programRunArgumentsTextArea;

    public MainViewSettingsEditor(Project project) {
        super();
        this.project = project;
    }

    @Override
    protected void resetEditorFrom(@NotNull RunConfiguration runConfiguration) {

    }

    @Override
    protected void applyEditorTo(@NotNull RunConfiguration configuration) throws ConfigurationException {
        // TODO: I do not know how data applies from editor to configuration and vice versa

        if (!scriptNameTextField.getText().isEmpty() && !scriptNameTextField.getText().equals(configuration.getScriptFilename())) {
            configuration.setScriptFilename(scriptNameTextField.getText());
        } else {
            scriptNameTextField.setText(configuration.getScriptFilename());
        }

        if (!programRunArgumentsTextArea.getText().isEmpty() && !programRunArgumentsTextArea.getText().equals(configuration.getProgramRunParameters())) {
            configuration.setProgramRunParameters(programRunArgumentsTextArea.getText());
        } else {
            programRunArgumentsTextArea.setText(configuration.getProgramRunParameters());
        }
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JPanel mainPanel = new JPanel();

        {
            LabeledComponent<ComponentWithBrowseButton> scriptNameLabel = new LabeledComponent<ComponentWithBrowseButton>();
            scriptNameLabel.setText("Script filename");

            FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
            scriptNameTextField = new TextFieldWithBrowseButton();
            scriptNameTextField.addBrowseFolderListener("Choose golang script", "Choose file", project, descriptor);
            scriptNameLabel.setComponent(scriptNameTextField);

            mainPanel.add(scriptNameLabel);
        }

        {
            LabeledComponent<JTextArea> argumentsLabel = new LabeledComponent<JTextArea>();
            argumentsLabel.setText("Program run arguments");

            programRunArgumentsTextArea = new JTextArea();
            argumentsLabel.setComponent(programRunArgumentsTextArea);

            mainPanel.add(argumentsLabel);
        }

        return mainPanel;
    }
}
