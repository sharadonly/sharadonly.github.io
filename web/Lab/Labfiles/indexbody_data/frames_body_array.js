/*
 Milonic DHTML Menu
 Written by Andy Woolley
 Copyright 2002 (c) Milonic Solutions. All Rights Reserved.
 Plase vist http://www.milonic.co.uk/menu or e-mail menu3@milonic.com
 You may use this menu on your web site free of charge as long as you place prominent links to http://www.milonic.co.uk/menu and
 your inform us of your intentions with your URL AND ALL copyright notices remain in place in all files including your home page
 Comercial support contracts are available on request if you cannot comply with the above rules.

 Please note that major changes to this file have been made and is not compatible with versions 3.0 or earlier.

 You no longer need to number your menus as in previous versions. 
 The new menu structure allows you to name the menu instead, this means that you can remove menus and not break the system.
 The structure should also be much easier to modify, add & remove menus and menu items.
 
 If you are having difficulty with the menu please read the FAQ at http://www.milonic.co.uk/menu/faq.php before contacting us.

 Please note that the above text CAN be erased if you wish as long as copyright notices remain in place.
*/

//The following line is critical for menu operation, and MUST APPEAR ONLY ONCE. If you have more than one menu_array.js file rem out this line in subsequent files
menunum=0;menus=new Array();_d=document;function addmenu(){menunum++;menus[menunum]=menu;}function dumpmenus(){mt="<script language=javascript>";for(a=1;a<menus.length;a++){mt+=" menu"+a+"=menus["+a+"];"}mt+="<\/script>";_d.write(mt)}
//Please leave the above line intact. The above also needs to be enabled if it not already enabled unless this file is part of a multi pack.



////////////////////////////////////
// Editable properties START here //
////////////////////////////////////


// Special effect string for IE5.5 or above please visit http://www.milonic.co.uk/menu/filters_sample.php for more filters
if(navigator.appVersion.indexOf("MSIE 6.0")>0){
	effect = "Fade(duration=0.2);Alpha(style=0,opacity=88);Shadow(color='#777777', Direction=135, Strength=5)"
}
else{
	effect = "Shadow(color='#777777', Direction=135, Strength=5)"
}


timegap=500				// The time delay for menus to remain visible
followspeed=5				// Follow Scrolling speed
followrate=40				// Follow Scrolling Rate
suboffset_top=10;			// Sub menu offset Top position 
suboffset_left=10;			// Sub menu offset Left position
Frames_Top_Offset=1000 		// Frames Page Adjustment for Top



plain_style=[				// Menu Properties Array
"white",					// Mouse Off Font Color
"8CA7C5",				// Mouse Off Background Color
"FCD800",				// Mouse On Font Color
"041955",				// Mouse On Background Color
"000080",				// Menu Border Color 
12,						// Font Size in pixels
"normal",				// Font Style (italic or normal)
"bold",					// Font Weight (bold or normal)
"Verdana, Arial",		// Font Name
4,							// Padding
"includes/arrow.gif"					// Sub Menu Image
,							// 3D Border & Separator
,"66ccff"					// 3D High Color
,"000099"					// 3D Low Color
]

        



	addmenu(menu=["m1",
		,,200,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"Home&nbsp;&nbsp;","..\\index.html","Recognition, Detection and Tracking",,0
//		,"Medical Image Processing&nbsp;&nbsp;","show-menu=acad",,"",0
				
		])



	addmenu(menu=["dns",
		,,200,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Detection and Tracking&nbsp;&nbsp;","face.html","Recognition, Detection and Tracking",,0
		,"Surveillance&nbsp;&nbsp;","surveillance.html",,,0
		,"Content Based Retrieval&nbsp;&nbsp;","cbir.html",,,0
		,"Image Processing&nbsp;&nbsp;","mic.html",,,0
		,"Agent Based Modeling&nbsp;&nbsp;","pc.html",,,0
		,"Robotics  &nbsp;&nbsp;","mda.html",,,0
	])



	addmenu(menu=["pub",
	,,120,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"2007","2007.html",,,0

	])
	


	addmenu(menu=["ppl",
	,,100,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"Members&nbsp;&nbsp;","show-menu=mem",,,0
	,"Associates&nbsp;&nbsp;","show-menu=assoc",,,0
	])

		addmenu(menu=["mem",
		,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Dr. Sadanand Srivastava", "http://www.cs.bowiestate.edu/Faculty_Pages/Dr._Srivastava/Srivastava_Page.htm target=new_frame",,,0
		,"Dr. Manohar Mareboyana &nbsp;&nbsp;","http://www.cs.bowiestate.edu/~manohar target=new_frame",,,0
		,"Dr. Bo Yang &nbsp;&nbsp;","http://www.cs.bowiestate.edu/Faculty_Pages/Mr._Yang/Yang_Page.htm target=new_frame",,,0
		,"Dr. Sharad Sharma  &nbsp;&nbsp;","http://www.cs.bowiestate.edu/sharad/ target=new_frame",,,0
		])

		addmenu(menu=["assoc",
		,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Dr. Langdon &nbsp;&nbsp;","http://www.cs.bowiestate.edu/Faculty_Pages/Dr._Langdon/Langdon_Page.htm target=new_frame",,,0
		,"Dr. Mark Matties &nbsp;&nbsp;","http://www.cs.bowiestate.edu/Faculty_Pages/Dr._Matties/Matties_Page.htm target=new_frame",,,0
		])


	addmenu(menu=["cntus",
	,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"Address &nbsp;&nbsp;","abc.html",,,0
	,"Website Administrator &nbsp;&nbsp;","http://www.cs.bowiestate.edu/sharad/",,,0
	])
	
dumpmenus()
