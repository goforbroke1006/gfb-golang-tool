package com.gfb.golang_profiler_tool.run.configuration;

import com.gfb.golang_profiler_tool.fileTypes.GolangFileType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.Key;
import org.apache.batik.util.gui.LanguageDialog;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by SCherk01 on 30.08.17.
 */
public class GolangProfilerSettingsEditor extends SettingsEditor<GolangProfilerRunConfiguration> {
    private Project project;
    private TextFieldWithBrowseButton fieldWithBrowseButton;

    public GolangProfilerSettingsEditor(Project project) {
        super();
        this.project = project;
    }

    @Override
    protected void resetEditorFrom(@NotNull GolangProfilerRunConfiguration golangProfilerRunConfiguration) {

    }

    @Override
    protected void applyEditorTo(@NotNull GolangProfilerRunConfiguration golangProfilerRunConfiguration) throws ConfigurationException {
        //golangProfilerRunConfiguration.putUserData(new Key<Object>("scriptName"), myMainClass.getRawText());

        if (!fieldWithBrowseButton.getText().equals("") && !fieldWithBrowseButton.getText().equals(golangProfilerRunConfiguration.getScriptFilename())) {
//            fieldWithBrowseButton.setText("WILDFOWL");
            golangProfilerRunConfiguration.setScriptFilename(fieldWithBrowseButton.getText());
        } else {
            fieldWithBrowseButton.setText(golangProfilerRunConfiguration.getScriptFilename());
        }
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JPanel myPanel = new JPanel();

        LabeledComponent<ComponentWithBrowseButton> myMainClass = new LabeledComponent<ComponentWithBrowseButton>();

        FileChooserDescriptor descriptor = FileChooserDescriptorFactory
                .createSingleFileDescriptor();
        fieldWithBrowseButton = new TextFieldWithBrowseButton();
        fieldWithBrowseButton.addBrowseFolderListener("Choose golang script", "Choose file", project, descriptor);
        myMainClass.setComponent(fieldWithBrowseButton);

        myPanel.add(myMainClass);

        return myPanel;
    }
}
