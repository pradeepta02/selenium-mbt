package com.cleartrip.mbt;

import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;
import org.graphwalker.java.annotation.Edge;

@Model(file = "com/cleartrip/mbt/graphml/cleartrip.graphml")
public interface IBaseModel {

    @Edge()
    void e_StartBrowser();

    @Edge()
    void e_SearchForFlight();

    @Edge()
    void e_SelectFlight();

    @Edge()
    void e_ContinueBooking();

    @Edge()
    void e_AddEmailAddress();

    @Edge()
    void e_NavigateToCalendarPage();

    @Edge()
    void e_SelectLowestPriceFlight();

    @Edge()
    void e_CancelAddMeals();

    @Edge()
    void e_CancelSelectSeats();

    @Edge()
    void e_SelectSeats();

    @Edge()
    void e_AddMeals();

    @Edge()
    void e_NavigateToHomePage();

    @Vertex()
    void v_HomePage();

    @Vertex()
    void v_ListPage();

    @Vertex()
    void v_BookingsPage();

    @Vertex()
    void v_EmailAddressPage();

    @Vertex()
    void v_TravellerDetailsPage();

    @Vertex()
    void v_AddMealsPage();

    @Vertex()
    void v_SelectSeatsPage();
}
