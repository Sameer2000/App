package com.housee.app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housee.app.json.GameList;
import com.housee.app.json.GameRoot;
import com.housee.app.json.RegisterRoot;
import com.housee.app.json.RoomsList;
import com.housee.app.json.Root;
import com.housee.app.json.UserTotalBalance;
import com.housee.app.model.Player;
import com.housee.app.model.Room;
import com.housee.app.model.Ticket;

public interface RestController {


	public @ResponseBody RegisterRoot authenticateUserProfile(@RequestBody RegisterRoot root);
	
	public @ResponseBody Root registerUserProfile(@RequestBody RegisterRoot root);

	public @ResponseBody RoomsList getRoomsList(@RequestBody Root root);

	public @ResponseBody GameList getGamesList(@RequestBody GameRoot root);
	
	public @ResponseBody Player getPlayer(@RequestBody GameRoot root);
	
	public @ResponseBody Ticket getTicket(@RequestBody Root root);
	
	public @ResponseBody String confirmTicket(@RequestBody GameRoot root);

	public @ResponseBody Room getRoom(@RequestBody Root root);

	public @ResponseBody UserTotalBalance getAccountInfo(@RequestBody Root root);

	



}
