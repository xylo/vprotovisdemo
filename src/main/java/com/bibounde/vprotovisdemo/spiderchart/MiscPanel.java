package com.bibounde.vprotovisdemo.spiderchart;

import com.bibounde.vprotovisdemo.Page;
import com.bibounde.vprotovisdemo.util.OpacityValidator;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.DoubleValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

public class MiscPanel implements Page {

    private static final String ENABLED_OPTION = "enabled";
    private static final String DISABLED_OPTION = "disabled";
    private static final String CUSTOM_OPTION = "Custom";
    private static final String DEFAULT_OPTION = "Default";
    private static final String RANDOM_OPTION = "Random";
    
    private GridLayout content;
    private NativeSelect legendBox, tooltipBox, colorBox, areaBox;
    private Label legendAreaWidthLabel, legendInsetLeftLabel, areaOpacityLabel;
    private TextField legendAreaWidthText, legendInsetLeftText, areaOpacityText;
    
    public MiscPanel() {
        this.initLayout();
        this.initListener();
        this.initValidator();
        this.initValues();
    }

    private void initLayout() {
        this.content = new GridLayout(8, 4);
        this.content.setMargin(true);
        this.content.setSpacing(true);
        
        Label legendLabel = new Label("Legend : ");
        this.content.addComponent(legendLabel, 0, 0);
        this.content.setComponentAlignment(legendLabel, Alignment.MIDDLE_LEFT);

        this.legendBox = new NativeSelect();
        this.legendBox.setNullSelectionAllowed(false);
        this.legendBox.setWidth("110px");
        this.legendBox.addItem(ENABLED_OPTION);
        this.legendBox.addItem(DISABLED_OPTION);
        this.legendBox.setImmediate(true);
        this.content.addComponent(this.legendBox, 1, 0);
        this.content.setComponentAlignment(this.legendBox, Alignment.MIDDLE_LEFT);

        this.legendAreaWidthLabel = new Label("Legend area width : ");
        this.content.addComponent(legendAreaWidthLabel, 2, 0);
        this.content.setComponentAlignment(legendAreaWidthLabel, Alignment.MIDDLE_LEFT);

        this.legendAreaWidthText = new TextField();
        this.legendAreaWidthText.setWidth("110px");
        this.legendAreaWidthText.setImmediate(true);
        this.content.addComponent(this.legendAreaWidthText, 3, 0);
        this.content.setComponentAlignment(this.legendAreaWidthText, Alignment.MIDDLE_LEFT);
        
        this.legendInsetLeftLabel = new Label("Legend inset left : ");
        this.content.addComponent(legendInsetLeftLabel, 4, 0);
        this.content.setComponentAlignment(legendInsetLeftLabel, Alignment.MIDDLE_LEFT);

        this.legendInsetLeftText = new TextField();
        this.legendInsetLeftText.setWidth("110px");
        this.legendInsetLeftText.setImmediate(true);
        this.content.addComponent(this.legendInsetLeftText, 5, 0);
        this.content.setComponentAlignment(this.legendInsetLeftText, Alignment.MIDDLE_LEFT);
        
        Label tooltipLabel = new Label("Tooltip : ");
        this.content.addComponent(tooltipLabel, 0, 1);
        this.content.setComponentAlignment(tooltipLabel, Alignment.MIDDLE_LEFT);

        this.tooltipBox = new NativeSelect();
        this.tooltipBox.setNullSelectionAllowed(false);
        this.tooltipBox.setWidth("110px");
        this.tooltipBox.addItem(DEFAULT_OPTION);
        this.tooltipBox.addItem(CUSTOM_OPTION);
        this.tooltipBox.addItem(DISABLED_OPTION);
        this.tooltipBox.setImmediate(true);
        this.content.addComponent(this.tooltipBox, 1, 1);
        this.content.setComponentAlignment(this.tooltipBox, Alignment.MIDDLE_LEFT);
        
        Label colorLabel = new Label("Colors : ");
        this.content.addComponent(colorLabel, 0, 2);
        this.content.setComponentAlignment(colorLabel, Alignment.MIDDLE_LEFT);

        this.colorBox = new NativeSelect();
        this.colorBox.setNullSelectionAllowed(false);
        this.colorBox.setWidth("110px");
        this.colorBox.addItem(DEFAULT_OPTION);
        this.colorBox.addItem(RANDOM_OPTION);
        this.colorBox.setImmediate(true);
        this.content.addComponent(this.colorBox, 1, 2);
        this.content.setComponentAlignment(this.colorBox, Alignment.MIDDLE_LEFT);
        
        Label areaLabel = new Label("Area : ");
        this.content.addComponent(areaLabel, 0, 3);
        this.content.setComponentAlignment(areaLabel, Alignment.MIDDLE_LEFT);

        this.areaBox = new NativeSelect();
        this.areaBox.setNullSelectionAllowed(false);
        this.areaBox.setWidth("110px");
        this.areaBox.addItem(ENABLED_OPTION);
        this.areaBox.addItem(DISABLED_OPTION);
        this.areaBox.setImmediate(true);
        this.content.addComponent(this.areaBox, 1, 3);
        this.content.setComponentAlignment(this.areaBox, Alignment.MIDDLE_LEFT);
        
        this.areaOpacityLabel= new Label("Opacity : ");
        this.content.addComponent(areaOpacityLabel, 2, 3);
        this.content.setComponentAlignment(areaOpacityLabel, Alignment.MIDDLE_LEFT);

        this.areaOpacityText = new TextField();
        this.areaOpacityText.setWidth("110px");
        this.areaOpacityText.setImmediate(true);
        this.content.addComponent(this.areaOpacityText, 3, 3);
        this.content.setComponentAlignment(this.areaOpacityText, Alignment.MIDDLE_LEFT);
    }
    
    private void initListener() {
        this.legendBox.addListener(new ValueChangeListener() {
            
            public void valueChange(ValueChangeEvent event) {
                boolean enabled = ENABLED_OPTION.equals(event.getProperty().getValue());
                legendAreaWidthText.setEnabled(enabled);
                legendAreaWidthLabel.setEnabled(enabled);
                legendInsetLeftLabel.setEnabled(enabled);
                legendInsetLeftText.setEnabled(true);
            }
        });
        
        this.areaBox.addListener(new ValueChangeListener() {
            
            public void valueChange(ValueChangeEvent event) {
                boolean enabled = ENABLED_OPTION.equals(event.getProperty().getValue());
                areaOpacityLabel.setEnabled(enabled);
                areaOpacityText.setEnabled(enabled);
            }
        });
    }
    
    private void initValidator() {
        DoubleValidator doubleValidator = new DoubleValidator("Value must be a number");
        
        this.legendAreaWidthText.setRequiredError("Legend area must be set");
        this.legendAreaWidthText.setRequired(true);
        this.legendAreaWidthText.addValidator(doubleValidator);
        this.legendInsetLeftText.addValidator(doubleValidator);
        
        this.areaOpacityText.addValidator(new OpacityValidator());
    }

    public Component getComponent() {
        return this.content;
    }
    
    private void initValues() {
        this.legendBox.setValue(ENABLED_OPTION);
        this.legendAreaWidthText.setValue("150");
        this.legendInsetLeftText.setValue("50");
        this.colorBox.setValue(DEFAULT_OPTION);
        this.tooltipBox.setValue(ENABLED_OPTION);
        this.areaBox.setValue(ENABLED_OPTION);
        this.tooltipBox.setValue(ENABLED_OPTION);
    }

    public boolean validate() {
        try {
            if (ENABLED_OPTION.equals(this.legendBox.getValue())) {
                this.legendAreaWidthText.validate();
                this.legendInsetLeftText.validate();
            }
            if (ENABLED_OPTION.equals(this.areaBox.getValue())) {
                this.areaOpacityText.validate();
            }
            return true;
        } catch (Exception e) {
            //Do nothing. All is done in ui
            return false;
        }
    }
    
    public boolean isLegendEnabled() {
        return ENABLED_OPTION.equals(this.legendBox.getValue());
    }
    
    public Double getLegendAreaWidth() {
        return Double.valueOf((String) this.legendAreaWidthText.getValue());
    }
    
    public boolean isRandomColorSelected() {
        return RANDOM_OPTION.equals(this.colorBox.getValue());
    }
    
    public boolean isTooltipEnabled() {
        return !DISABLED_OPTION.equals(this.tooltipBox.getValue());
    }
    
    public boolean isTooltipCustomEnabled() {
        return isTooltipEnabled() && CUSTOM_OPTION.equals(this.tooltipBox.getValue());
    }
    
    public Double getLegendInsetLeft() {
        return this.legendInsetLeftText.getValue().equals("") ? null : Double.valueOf((String) this.legendInsetLeftText.getValue());
    }
    
    public boolean isAreModeEnabled() {
        return ENABLED_OPTION.equals(this.areaBox.getValue());
    }
    
    public Double getAreaOpacity() {
        return this.areaOpacityText.getValue().equals("") ? null : Double.valueOf((String) this.areaOpacityText.getValue());
    }
}