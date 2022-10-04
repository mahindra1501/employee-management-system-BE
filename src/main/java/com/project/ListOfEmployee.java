
package com.project;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.project.model.EmployeeModel;
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
import com.project.controller.EmployeeController;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("OMS CRUD Software...")
@Route(value = "employee", layout = masterView.class)
public class ListOfEmployee extends VerticalLayout {

	EmployeeModel employeeModel = new EmployeeModel();

	EmployeeController employeeController;

	public ListOfEmployee(EmployeeController employeeController) {
		this.employeeController = employeeController;

		HorizontalLayout horiLayout = new HorizontalLayout();
		Icon userIcon = new Icon(VaadinIcon.USER);
		Button addBtn = new Button(userIcon);
		addBtn.setText("Add Employee");
		horiLayout.add(addBtn);
		try {
			Grid<EmployeeModel> grid = new Grid<>();
			List<EmployeeModel> listOfEmployee = employeeController.showAllEmployee();
			System.out.println(listOfEmployee + "----------->>>>>>>>>>>>");
			grid.setAllRowsVisible(true);
			grid.addColumn(EmployeeModel -> EmployeeModel.getEID()).setHeader("Employee ID ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getfName()).setHeader("First Name ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getlName()).setHeader("Last Name  ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getOffice()).setHeader("Office ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getDate()).setHeader("Date ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getContact()).setHeader("Contact ");
			grid.addColumn(EmployeeModel -> EmployeeModel.getRole()).setHeader("Role ");
			grid.setItems(listOfEmployee);
			add(horiLayout, grid);
			addBtn.addClickListener(e -> {
				grid.setVisible(false);
			});
			grid.addComponentColumn(events -> {
				HorizontalLayout layouts = new HorizontalLayout();
				Icon editIcon = new Icon(VaadinIcon.EDIT);
				Button editBtn = new Button(editIcon);
				editBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
				editBtn.addClassName("button2");
				editBtn.addClickListener(e -> {

					grid.setVisible(false);
					EmployeeModel singleEmployee = employeeController.getEmployee(events.getEID());
					editEmployee(singleEmployee);
				});
				layouts.add(editBtn);
				return layouts;
			}).setHeader("Edit Employee");

			grid.addComponentColumn(event -> {
				HorizontalLayout layouts = new HorizontalLayout();
				Icon deleteIcon = new Icon(VaadinIcon.TRASH);
				Button delBtn = new Button(deleteIcon);
				delBtn.addClassName("button2");
				delBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

				delBtn.addClickListener(e -> {
					employeeController.deleteEmployee(event.getEID());
					Notification notification = Notification.show("Employee is deleted Successfully !");
					notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
					notification.setPosition(Notification.Position.TOP_CENTER);
					UI.getCurrent().getPage().reload();
				});
				layouts.add(delBtn);
				return layouts;
			}).setHeader("Delete Employee");

			grid.setItems(listOfEmployee);
			add(horiLayout, grid);

			addBtn.addClickListener(e -> {
				grid.setVisible(false);
				addBtn.setVisible(false);
				addEmployeeForm();

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private String selectAddress(ComboBox<String> profession) {
		profession.setAllowCustomValue(true);
		profession.setItems("Pune", "Mumbai", "chennai", "Gurgaon", "bangalore	");
		String prof = profession.getValue();
		return prof;
	}

	private String selectRole(ComboBox<String> profession) {
		profession.setAllowCustomValue(true);
		profession.setItems("HR", "Developer", "Tester", "Manager", "Team Lead");
		String prof = profession.getValue();
		return prof;
	}

	private String Dates(DatePicker datePicker) {
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		System.out.println(date);
		return date;
	}

	public void header() {
		H3 txt = new H3("Add Employee");
		add(txt);
	}

	private void addEmployeeForm() {
		header();

		TextField fName = new TextField("Name");
		fName.addClassName("curveOutline");
		fName.setPlaceholder("First Name...");

		TextField lName = new TextField();
		lName.addClassName("curveOutline");
		lName.setPlaceholder("Last Name...");

		DatePicker datePicker = new DatePicker("Select Date");
		datePicker.addClassName("curveOutline");
		datePicker.setPlaceholder("Date of Joining");

		ComboBox<String> role = new ComboBox<>("Employee Role");
		role.addClassName("curveOutline");
		role.setPlaceholder("Select Role");
		role.addValueChangeListener(event -> {
			role.getValue().toString();
		});
		selectRole(role);

		ComboBox<String> office = new ComboBox<>("Office Location");
		office.addClassName("curveOutline");
		office.setPlaceholder("Select Office");
		office.addValueChangeListener(event -> {
			office.getValue().toString();
		});
		selectAddress(office);

		TextField contact = new TextField("Contact No");
		contact.addClassName("curveOutline");
		contact.setPlaceholder("+91 XXX XXX XXXX");

		FormLayout formLayout = new FormLayout();
		formLayout.add(fName, lName, role, office, datePicker, contact);

		Button save = new Button("save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickShortcut(Key.ENTER);
		save.addClassName("button2");
		Button cancel = new Button("Cancel");
		cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cancel.addClickShortcut(Key.ESCAPE);
		cancel.addClassName("button2");

		save.addClickListener(event -> {
			employeeModel.setfName(fName.getValue());
			employeeModel.setlName(lName.getValue());
			employeeModel.setOffice(office.getValue());
			employeeModel.setRole(role.getValue());
			employeeModel.setDate(Dates(datePicker));
			employeeModel.setContact(contact.getValue());

			employeeController.saveEmployee(employeeModel);
			Notification notification = Notification.show("Employee created Successfully !");
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

	private void editEmployee(EmployeeModel employee) {

		Binder<EmployeeModel> binder = new Binder<EmployeeModel>();
		binder.setBean(employee);

		TextField fName = new TextField("First name");
		fName.addClassName("curveOutline");
		binder.forField(fName).asRequired().bind(EmployeeModel::getfName, EmployeeModel::setfName);

		TextField lName = new TextField("Last name");
		lName.addClassName("curveOutline");
		binder.forField(lName).asRequired().bind(EmployeeModel::getlName, EmployeeModel::setlName);

		TextField office = new TextField("Email");
		office.addClassName("curveOutline");
		binder.forField(office).asRequired().bind(EmployeeModel::getOffice, EmployeeModel::setOffice);

		TextField role = new TextField("Email");
		role.addClassName("curveOutline");
		binder.forField(role).asRequired().bind(EmployeeModel::getRole, EmployeeModel::setRole);

		TextField date = new TextField("Date ");
		date.addClassName("curveOutline");
		binder.forField(date).asRequired().bind(EmployeeModel::getDate, EmployeeModel::setDate);

		TextField contact = new TextField("Contact No");
		contact.addClassName("curveOutline");
		binder.forField(contact).asRequired().bind(EmployeeModel::getContact, EmployeeModel::setContact);

		FormLayout formLayout = new FormLayout();
		formLayout.add(fName, lName, office, role, date, contact);

		Button save = new Button("save");
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClickShortcut(Key.ENTER);
		save.addClassName("button2");

		save.addClickListener(event -> {
			employeeController.editEmployee(binder.getBean(), binder.getBean().getEID());

			Notification notification = Notification.show("Employee Edited Successfully !");
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
			employeeController.deleteEmployee(employee.getEID());
			UI.getCurrent().getPage().reload();
			Notification notification = Notification.show("Employee is deleted Successfully !");
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
