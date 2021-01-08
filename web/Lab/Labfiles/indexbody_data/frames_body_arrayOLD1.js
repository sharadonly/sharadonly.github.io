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
		,"Home&nbsp;&nbsp;","../index.html","Face Recognition, Detection and Tracking",,0
				
		])


	addmenu(menu=["dns",
	,,200,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Detection and Tracking&nbsp;&nbsp;","face.html","Recognition, Detection and Tracking",,0

		,"Surveillance&nbsp;&nbsp;","surveillance.html",,,0
		,"Content Based Retrieval&nbsp;&nbsp;","cbir.html",,,0
		,"Medical Image Processing&nbsp;&nbsp;","mic.html",,,0
		,"Pattern Classification&nbsp;&nbsp;","pc.html",,,0
		,"Medical Data Analysis&nbsp;&nbsp;","mda.html",,,0
	])



	addmenu(menu=["pub",
	,,120,1,"",plain_style,,"left",effect,,,,,,,,,,,,
//	,"Alumni Association&nbsp;&nbsp;","http://www.oakland.edu/ target=new_frame",,,0
	,"2007","2007.html",,,0
	,"2006","2006.html",,,0
	,"2005","2005.html",,,0
	,"2004","2004.html",,,0
	,"2003","2003.html",,,0
	,"2002","2002.html",,,0
	,"2001","2001.html",,,0
	,"2000","2000.html",,,0
	,"1999","1999.html",,,0
	,"1998","1998.html",,,0
	,"1997","1997.html",,,0
	,"1996","1996.html",,,0
	,"Prior","prior.html",,,0
	])
	
//	addmenu(menu=["pts",
//	,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
//	 ,"Deep View Systems&nbsp;&nbsp;","http://www.deepview.com/ target=new_frame",,,0
//	 ,"Mandala Sciences&nbsp;&nbsp;","",,,0
//	 ,"Accenture&nbsp;&nbsp;","http://www.accenture.com/ target=new_frame",,,0
//	,"Guardian Industries&nbsp;&nbsp;","http://www.guardian.com/ target=new_frame",,,0
//	])

	addmenu(menu=["ppl",
	,,100,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"Members&nbsp;&nbsp;","show-menu=mem",,,0
	,"Associates&nbsp;&nbsp;","show-menu=assoc",,,0
	])

		addmenu(menu=["mem",
		,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Ishwar K. Sethi", "http://www.cse.secs.oakland.edu/isethi/index.html target=new_frame",,,0
		,"Aiyesha Ma&nbsp;&nbsp;","http://iielab-secs.secs.oakland.edu/people/aiyesha target=new_frame",,,0
		,"Ali Mustafa &nbsp;&nbsp;","http://iielab-secs.secs.oakland.edu/people/amustafa target=new_frame",,,0
		,"Dingguo Chen&nbsp;&nbsp;","dchen.html",,,0
		,"Jie Ouyang;&nbsp","",,,0
		])

		addmenu(menu=["assoc",
		,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
		,"Andrey&nbsp;&nbsp;","http://ltu164.ltu.edu/~andrey/ target=new_frame",,,0
		,"Bo Shen&nbsp;&nbsp;","http://www.hpl.hp.com/personal/Bo_Shen/ target=new_frame",,,0
		,"Daniella Stan&nbsp;&nbsp;","http://facweb.cs.depaul.edu/dstan/main.htm target=new_frame",,,0
		,"Dongge Li&nbsp;&nbsp;","http://iielab-secs.secs.oakland.edu/people/li.html target=new_frame",,,0
		,"Gang Wei&nbsp;&nbsp;","http://www.accenture.com/xd/xd.asp?it=enweb&xd=services/technology/people/gang_wei.xml target=new_frame",,,0
		,"Huzaifa Rampurawala&nbsp;&nbsp;","hrampur.html",,,0
		,"Loana L. Coman&nbsp;&nbsp;","http://web.syr.edu/~ilcoman/ target=new_frame",,,0
		,"MingKun Li&nbsp;&nbsp;","http://www.oakland.edu/~li target=new_frame",,,0
		,"Nilesh Patel&nbsp;&nbsp;","http://www.engin.umd.umich.edu/CIS/ target=new_frame",,,0
		,"Oktay Ibrahimov &nbsp;&nbsp;","http://www.oakland.edu/~ibrahimo target=new_frame",,,0
		,"Raghu Venkatram&nbsp;&nbsp;","http://www.oakland.edu/~rvenkatr/ target=new_frame",,,0
		,"Rohit Rathee&nbsp;&nbsp;","rrathee.html",,,0
		,"Shuo Feng", "",,,0
		,"Victor kulesh&nbsp;&nbsp;","http://iielab-secs.secs.oakland.edu/people/victor target=new_frame",,,0
		,"Vijayan Sugumaran&nbsp;&nbsp;","",,,0
		])


	addmenu(menu=["cntus",
	,,150,1,"",plain_style,,"left",effect,,,,,,,,,,,,
	,"Ishwar K. Sethi&nbsp;&nbsp;","isethi.html",,,0
	,"Website Administrator&nbsp;&nbsp;","webadmin.html",,,0
	])
	
dumpmenus()
