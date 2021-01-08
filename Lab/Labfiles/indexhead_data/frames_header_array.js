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


timegap=500					// The time delay for menus to remain visible
followspeed=5				// Follow Scrolling speed
followrate=40				// Follow Scrolling Rate
suboffset_top=100			// Sub menu offset Top position 
suboffset_left=100			// Sub menu offset Left position
Frames_Top_Offset=0 		// Frames Page Adjustment for Top
Frames_Left_Offset=100	// Frames Page Adjustment for Left


plain_style=[			// style1 is an array of properties. You can have as many property arrays as you need. This means that menus can have their own style.
"white",					// Mouse Off Font Color
"8CA7C5",				// Mouse Off Background Color
"FCD800",				// Mouse On Font Color
"041955",				// Mouse On Background Color
"000080",				// Menu Border Color 
12,						// Font Size in pixels
"normal",				// Font Style (italic or normal)
"bold",					// Font Weight (bold or normal)
"Verdana, Arial",		// Font Name
4,					// Menu Item Padding
"includes/arrow.gif",		// Sub Menu Image (Leave this blank if not needed)
,					// 3D Border & Separator bar
"66ffff",			// 3D High Color
"000099",			// 3D Low Color
"Gray",			// Current Page Item Font Color (leave this blank to disable)
"pink",				// Current Page Item Background Color (leave this blank to disable)
"includes/arrowdn.gif",		// Top Bar image (Leave this blank to disable)
"ffffff",			// Menu Header Font Color (Leave blank if headers are not needed)
"000099",			// Menu Header Background Color (Leave blank if headers are not needed)
"navy",			// Menu Item Separator Color
]

addmenu(menu=[		// This is the array that contains your menu properties and details
"simplemenu",			// Menu Name - This is needed in order for the menu to be called
120,					// Menu Top - The Top position of the menu in pixels
10,				// Menu Left - The Left position of the menu in pixels
,					// Menu Width - Menus width in pixels
1,					// Menu Border Width 
,					// Screen Position - here you can use "center;left;right;middle;top;bottom" or a combination of "center:middle"
plain_style,				// Properties Array - this is set higher up, as above
1,					// Always Visible - allows the menu item to be visible at all time (1=on/0=off)
"left",				// Alignment - sets the menu elements text alignment, values valid here are: left, right or center
effect,				// Filter - Text variable for setting transitional effects on menu activation
,					// Follow Scrolling - Tells the menu item to follow the user down the screen
1, 					// Horizontal Menu - Tells the menu to be horizontal instead of top to bottom style
,					// Keep Alive - Keeps the menu visible until the user moves over another menu or clicks elsewhere on the page
,					// Position of sub image left:center:right:middle:top:bottom
,					// Show an image on top menu bars indicating a sub menu exists below
,					// Reserved for future use
,					// Reserved for future use
,					// Reserved for future use
,,,
//,"Home","/menu/ target=_top;sourceframe=main;",,,1 // "Description Text", "URL", "Alternate URL", "Status", "Separator Bar"
//,"Home&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=p1 target=main;sourceframe=main;",,,1
,"Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=m1 target=main;sourceframe=main;",,,1
,"Research Projects&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=dns target=main;sourceframe=main;",,,1
,"Publications&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=pub target=main;sourceframe=main;",,,1
,"Partners&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","partners.html target=main;sourceframe=main;",,,1
,"People&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=ppl target=main;sourceframe=main;",,,1
,"Contact Us&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","show-menu=cntus target=main;sourceframe=main;",,,1
])

dumpmenus()