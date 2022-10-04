package com.project;

import java.time.format.DateTimeFormatter;
import java.util.List;


import com.project.model.ClientModel;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.project.controller.ClientController;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
@PageTitle("OMS CRUD Software...")
@Route(value = "clients", layout = masterView.class)
public class ListofClients extends VerticalLayout {

	ClientModel clientModel = new ClientModel();

	ClientController clientController;

	public ListofClients(ClientController clientController) {
		this.clientController = clientController;

		HorizontalLayout horiLayout = new HorizontalLayout();
		Icon userIcon = new Icon(VaadinIcon.USER);
		Button addBtn = new Button(userIcon);
		addBtn.setText("Add Client");
		horiLayout.add(addBtn);

		try {
			Grid<ClientModel> grid = new Grid<>();
			List<ClientModel> listOfClients = clientController.showAllClient();
			System.out.println(listOfClients + "----------->>>>>>>>>>>>");
			grid.setAllRowsVisible(true);
			grid.addColumn(ClientModel -> ClientModel.getCID()).setHeader("Client ID");
			grid.addColumn(ClientModel -> ClientModel.getfName()).setHeader("First Name ");
			grid.addColumn(ClientModel -> ClientModel.getlName()).setHeader("Last Name ");
			grid.addColumn(ClientModel -> ClientModel.getProject()).setHeader("Project ");
			grid.addColumn(ClientModel -> ClientModel.getContact()).setHeader("Contact ");
			grid.addColumn(ClientModel -> ClientModel.getDate()).setHeader("Date ");
			grid.addComponentColumn(events -> {
				HorizontalLayout layouts = new HorizontalLayout();
				Icon editIcon = new Icon(VaadinIcon.EDIT);
				Button editBtn = new Button(editIcon);			editBtn.addClassName("button2");
				editBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

				editBtn.addClickListener(e -> {

					grid.setVisible(false);
					ClientModel singleClient = clientController.getClient(events.getCID());
					editClient(singleClient);
				});
				layouts.add(editBtn);
				return layouts;
			}).setHeader("Edit Client");

			grid.addComponentColumn(event -> {
				HorizontalLayout layouts = new HorizontalLayout();
				Icon deleteIcon = new Icon(VaadinIcon.TRASH);
				Button delBtn = new Button(deleteIcon);				delBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

				delBtn.addClassName("button2");

				delBtn.addClickListener(e -> {
					clientController.deleteClient(event.getCID());
					Notification notification = Notification.show("Client is deleted Successfully !");
					notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
					notification.setPosition(Notification.Position.TOP_CENTER);
					UI.getCurrent().getPage().reload();
				});
				layouts.add(delBtn);
				return layouts;
			}).setHeader("Delete Client");

			grid.setItems(listOfClients);
			add(horiLayout, grid);

			addBtn.addClickListener(e -> {
				grid.setVisible(false);
				addBtn.setVisible(false);
				addClientForm();

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String selectProf(ComboBox<String> profession) {
		profession.setAllowCustomValue(true);
		profession.setItems("Nextiva","SDS","SDX","Interactions","Qualys");
		String prof = profession.getValue();
		return prof;
	}

	private String Dates(DatePicker datePicker) {
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println(date);
		return date;
	}

	public void header() {
		H3 txt = new H3("Add User");
		add(txt);
	}

	private void addClientForm() {
		header();
		TextField fName = new TextField("Name");
		fName.setPlaceholder("First Name...");
		fName.addClassName("curveOutline");


		TextField lName = new TextField();
		lName.setPlaceholder("Last Name...");
		lName.addClassName("curveOutline");


		DatePicker datePicker = new DatePicker("Select Date");
		datePicker.addClassName("curveOutline");
		datePicker.setPlaceholder("Date of Joining");

		ComboBox<String> project = new ComboBox<>("Client Project");
		project.addClassName("curveOutline");
		project.setPlaceholder("Select Project");
		project.addValueChangeListener(event -> {
			project.getValue().toString();
		});
		selectProf(project);

		TextField contact = new TextField("Contact No");
		contact.setPlaceholder("+91 XXX XXX XXXX");
		contact.addClassName("curveOutline");


		FormLayout formLayout = new FormLayout();
		formLayout.add(fName, lName, project, datePicker, contact);

		Button save = new Button("save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickShortcut(Key.ENTER);
		save.addClassName("button2");

		Button cancel = new Button("Cancel");
		cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cancel.addClickShortcut(Key.ESCAPE);
		cancel.addClassName("button2");

		save.addClickListener(event -> {
			clientModel.setfName(fName.getValue());
			clientModel.setlName(lName.getValue());
			clientModel.setProject(project.getValue());
			clientModel.setDate(Dates(datePicker));
			clientModel.setContact(contact.getValue());

			clientController.saveClient(clientModel);
			Notification notification = Notification.show("Client created Successfully !");
			notification.setPosition(Notification.Position.TOP_CENTER);
			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			UI.getCurrent().getPage().reload();
		});

		cancel.addClickListener(event -> {
			UI.getCurrent().getPage().reload();
		});

		HorizontalLayout btnLayout = new HorizontalLayout(save, cancel);
		add(formLayout, btnLayout);
	}

	private void editClient(ClientModel client) {

		Binder<ClientModel> binder = new Binder<ClientModel>();
		binder.setBean(client);

		TextField fName = new TextField("First name");
		fName.addClassName("curveOutline");
		binder.forField(fName).asRequired().bind(ClientModel::getfName, ClientModel::setfName);

		TextField lName = new TextField("Last name");
		lName.addClassName("curveOutline");
		binder.forField(lName).asRequired().bind(ClientModel::getlName, ClientModel::setlName);

		TextField project = new TextField("Email");
		project.addClassName("curveOutline");
		binder.forField(project).asRequired().bind(ClientModel::getProject, ClientModel::setProject);

		TextField date = new TextField("Date ");
		date.addClassName("curveOutline");
		binder.forField(date).asRequired().bind(ClientModel::getDate, ClientModel::setDate);

		TextField contact = new TextField("Contact No");
		contact.addClassName("curveOutline");
		binder.forField(contact).asRequired().bind(ClientModel::getContact, ClientModel::setContact);

		FormLayout formLayout = new FormLayout();
		formLayout.add(fName, lName, project, date, contact);

		Button save = new Button("save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickShortcut(Key.ENTER);
		save.addClassName("button2");

		save.addClickListener(event -> {
			clientController.editClient(binder.getBean(), binder.getBean().getCID());

			Notification notification = Notification.show("Client Edited Successfully !");
			notification.setPosition(Notification.Position.TOP_CENTER);
			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			UI.getCurrent().getPage().reload();

		});

		Button cancel = new Button("Cancel");
		cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cancel.addClickShortcut(Key.ESCAPE);
		cancel.addClassName("button2");


		Icon deleteIcon = new Icon(VaadinIcon.TRASH);
		Button delete = new Button(deleteIcon);
		delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
		delete.addClickShortcut(Key.DELETE);
		delete.addClassName("button2");

		delete.addClickListener(event -> {
			clientController.deleteClient(client.getCID());
			UI.getCurrent().getPage().reload();
			Notification notification = Notification.show("Client is deleted Successfully !");
			notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
			notification.setPosition(Notification.Position.TOP_CENTER);
		});
		cancel.addClickListener(event -> {
			UI.getCurrent().getPage().reload();
		});
		HorizontalLayout btnLayout = new HorizontalLayout(save, cancel, delete);
		add(formLayout, btnLayout);
	}
}