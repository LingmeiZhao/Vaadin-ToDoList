package com.example;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class TodoItemLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;
    public TodoItemLayout(Todo todo) {
        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        addComponent(done);
        addComponentsAndExpand(text);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Binder<Todo> binder = new Binder<>(Todo.class);
        binder.bindInstanceFields(this);
        binder.setBean(todo);
    }
}
