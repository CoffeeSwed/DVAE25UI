package org.northernaurora.dvae25.GUI.survey.GUIComponent;

import org.northernaurora.dvae25.GUI.survey.factory.SurveyComponentFactory;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentTextTypes;
import org.northernaurora.dvae25.GUI.survey.factory.Types.SurveyComponentPanelTypes;
import javax.swing.*;

public class SurveyPageHeading extends JPanel {
    public SurveyPageHeading(String header, String subheader){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        if (header != null) {
            this.add(SurveyComponentFactory.createLabel(header, SurveyComponentTextTypes.HEADER));
        }
        if (subheader != null){
            this.add(SurveyComponentFactory.createLabel(subheader, SurveyComponentTextTypes.SUBHEADING));
        }
        this.setBackground(SurveyComponentFactory.getBackground(SurveyComponentPanelTypes.TITLEHEADING));
    }
}
