package com.example;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.example.Todo;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ShortBuffer;

@SpringUI
public class ToDoUI extends UI{
    private VerticalLayout root;
    @Autowired
    ToDoLayout toDoLayout;

    @Override
    public void init(VaadinRequest request){
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout(){
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader(){
        Label header = new Label("TODOS");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);

    }

    private void addForm(){
       HorizontalLayout formLayout = new HorizontalLayout();
       formLayout.setWidth("80%");
       TextField task = new TextField();
       Button add = new Button("Add");
       add.addStyleName(ValoTheme.BUTTON_PRIMARY);
       add.setIcon(VaadinIcons.PLUS);

       formLayout.addComponentsAndExpand(task);
       formLayout.addComponents(add);
       add.addClickListener(click -> {
           toDoLayout.add(new Todo(task.getValue()));
           task.clear();
           task.focus();
       });
       task.focus();
       add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
       root.addComponent(formLayout);
    }

    private void addTodoList(){
        toDoLayout.setWidth("80%");
        root.addComponent(toDoLayout);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", click -> {
            toDoLayout.deleteCompleted();
        }));
    }

}
