package com.project;

import com.project.controller.ClientController;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.RouterLink;

@PageTitle("OMS CRUD Software...")
@Route("")
public class masterView extends AppLayout {
	ClientController clientController;	

	public masterView(ClientController clientController) {
		this.clientController = clientController;
		ListofClients clients = new ListofClients(clientController);
		showRouterLayoutContent(clients);
		createHeader();
		createDrawer();
	}




	private void createHeader() {
		H1 logo = new H1("Office Management Software");
		logo.addClassName("heading");
		DrawerToggle DrawerToggle =new DrawerToggle();
		HorizontalLayout header = new HorizontalLayout(DrawerToggle, logo);
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setWidth("100%");
		addToNavbar(header);
	}

	private void createDrawer() {
		RouterLink Client = new RouterLink("Client", ListofClients.class);
		Client.setHighlightCondition(HighlightConditions.sameLocation());
		VerticalLayout Clientlayout = new VerticalLayout(Client);
		Clientlayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		addToDrawer(Clientlayout);
		Client.addClassName("button2");
		Client.addClassName("drawerBtn");

		RouterLink Employee = new RouterLink("Employee", ListOfEmployee.class);
		Employee.setHighlightCondition(HighlightConditions.sameLocation());
		VerticalLayout EmpLayout = new VerticalLayout(Employee);
		EmpLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		addToDrawer(EmpLayout);
		Employee.addClassName("button2");
		Employee.addClassName("drawerBtn");
	}
}