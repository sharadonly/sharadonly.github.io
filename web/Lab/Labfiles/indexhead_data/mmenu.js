/*
 Milonic DHTML Website Navigation Menu - Version 3.4.03
 Written by Andy Woolley - Copyright 2002 (c) Milonic Solutions Limited. All Rights Reserved.
 Please visit http://www.milonic.co.uk/menu or e-mail menu3@milonic.com for more information.
 
 The Free use of this menu is only available to Non-Profit, Educational & Personal web sites.
 Commercial and Corporate licenses  are available for use on all other web sites & Intranets.
 All Copyright notices MUST remain in place at ALL times and, please keep us informed of your 
 intentions to use the menu and send us your URL.
*/
Mtimer=setTimeout("rep_img()",99999);_d=document;smc=-1;MLoaded=0;ST=0;mspu=0;pu=0;
ns4=(_d.layers)?true:false
ns6=(navigator.userAgent.indexOf("Gecko")!=-1)?true:false
mac=(navigator.appVersion.indexOf("Mac")!=-1)?true:false
mac45=(navigator.appVersion.indexOf("MSIE 4.5")!=-1)?true:false
if(ns6||ns4)mac=false
loadWait=0;
opera=(navigator.userAgent.indexOf("Opera")!=-1)?true:false
ns61=(parseInt(navigator.productSub)>=20010726)?true:false
ie4=(!_d.getElementById&&_d.all)?true:false;ta=0;
konq=(navigator.userAgent.indexOf("Konqueror")!=-1)?true:false
mp=(ns6)?"pointer":"hand";oatop=0;aleft=0;oaleft=1;osy=0;oww=-1;owh=-1;frs=0;fre=0;nsmatch=0;okpgms=0;
var kel,a,am,oa,im,om,flta;hlarr=new Array();hlcnt=0;hll=0;Mname=new Array();parr=new Array();SwapIM=new Array();keyar=new Array();keyarC=0;keynum=0;MenuHasFocus=1;
ParentMenu=0;pf=0;gmi=20;ns6hif=0;timo=0;el=0;nshl=0;var omv=0;df=1;im=0;ofrac=0;omnu=0;kmnu=0;ac=22;m=1;sy=1;ShM=0;ww=0;wh=0;var imar=new Array();mr=",";
while(eval("window.menu"+m)){mr+="menu"+m+",";tmenu=eval("menu"+m);Mname[m]=tmenu[0].toLowerCase();m++}
mr=mr+" ";mr=mr.split("\,");menus=mr.length-1;var cgm=0;
function gmobj(mtxt){if(_d.getElementById){m=_d.getElementById(mtxt)}else if(_d.all){m=_d.all[mtxt]}else if(_d.layers){m=_d.layers[mtxt]}return m;}
function gmstyle(mtxt){m=gmobj(mtxt);if(!ns4){if(m)m=m.style;}return m;}
function spos(gm,t_,l_,h_,w_){px="px";if(ns4){px="";gms=gm;if(w_!=null)gms.clip.width=w_;if(h_!=null)gms.clip.height=h_;}else if(opera){px="";gms=gm.style;if(w_!=null)gms.pixelWidth=w_;if(h_!=null)gms.pixelHeight=h_}else{gms=gm.style;if(w_!=null)gms.width=w_+px;if(h_!=null)gms.height=h_+px;}if(t_!=null)gms.top=t_+px;if(l_!=null)gms.left=l_+px}
function gpos(gm){if(ns4){t_=gm.top;l_=gm.left;h_=gm.clip.height;w_=gm.clip.width;}else if(opera){t_=gm.style.pixelTop;l_=gm.style.pixelLeft;h_=gm.style.pixelHeight;w_=gm.style.pixelWidth;}else if(ns6){t_=gm.offsetTop;l_=gm.offsetLeft;h_=gm.offsetHeight;w_=gm.offsetWidth}else{if(mac){topM=gm.offsetParent.topMargin;if(!topM)topM=0;leftM=gm.offsetParent.leftMargin;if(!leftM)leftM=0;t_=parseInt(topM)+gm.offsetTop;l_=parseInt(leftM)+gm.offsetLeft;}else{t_=gm.offsetTop;l_=gm.offsetLeft;}h_=gm.offsetHeight;w_=gm.offsetWidth;}if(konq){w_=parseInt(gm.style.width);h_=parseInt(gm.style.height);}var gpa=new Array();gpa[0]=t_;gpa[1]=l_;gpa[2]=h_;gpa[3]=w_;return(gpa)}
function getMouseXY(e){if(ns4||ns6){MouseX=e.pageX;MouseY=e.pageY;}else{MouseX=event.clientX;MouseY=event.clientY}if(!opera&&_d.all){MouseX=MouseX+_d.body.scrollLeft;MouseY=MouseY+_d.body.scrollTop;if(_d.compatMode=="CSS1Compat")MouseY=MouseY+sy};return true;}
function parseLink(txt,what){txt=txt+";";lt="";if(what=="link"){if(txt.indexOf("&quot;")>0){lt=txt}else{sp=txt.indexOf(" ");lt=txt.substr(0,sp);}}else{sp=txt.indexOf(what)+what.length+1;if(sp>what.length+1){lt=txt.substr(sp,999);lt=lt.substr(0,lt.indexOf(";"))}}return lt}
function cHexColor(_c){if(_c.length==6){_c=_c.toUpperCase();x=0;while(x<6){hsh="#";if(_c.charCodeAt(x)>70){hsh="";x=6}x++}_c=hsh+_c}return _c}
function stch(ms){window.status=ms;return true}
function SDiv(nm,sh){if(ns4&&nm.indexOf(".")>0)tD=eval(nm); else tD=gmstyle(nm);if(tD)if(sh)tD.visibility="visible";else tD.visibility="hidden"}
function close_el(){for(xa=hlarr.length-1;xa>=0;xa--){ti=hlarr[xa].split("_");tmenu=eval(ti[0]);if(ns4){shl(ti[0],ti[1],'hide');}else{arg=gmobj("el"+ti[1]);arg.style.background=ti[2];arg.style.color=""+ti[3];}if(ti[0]==hll){if(ns4)shl(ti[0],hel,'show');return}}hlcnt=0;hlarr=new Array()}
function closeallmenus(){ShM=0;om="";hll="";close_el();if(pf&&pf.MLoaded)pf.closeallmenus();if(ParentMenu){ParentMenu.close_el()}if(ns4){omv.visibility="hide"}for(a=1;a<menus;a++){menu=eval("menu"+a);if(menu[17]&&MLoaded)SDiv(menu[17],1);if(menu[7]!=1)SDiv("menu"+a,0);else am=""}}
function rep_img(){if(timo){timo.src=o_img}}
function popdn(){arg=popdn.arguments;if(arg[0]){if(arg[3].substr(0,5)!="show-"){if(ns4){shl(arg[2],arg[1],"hide")}else{arg[0].style.background=arg[4];arg[0].style.color=arg[5]}}else{if(ns4)omv=0;hlarr[hlcnt]=arg[2]+"_"+arg[1]+"_"+arg[4]+"_"+arg[5];hlcnt++;}menu=eval(arg[2]);if(!menu[12]){Mtimer=setTimeout("closeallmenus()",timegap);}}else{Mtimer=setTimeout("closeallmenus()",timegap);}}
function dc(){if(nshl.indexOf("show-menu=")>-1)return;tr=ltarg.split("=");if(tr[1])parent.frames[tr[1]].location.href=nshl;else location.href=nshl;}
function getMenuByName(mn){mn=mn.toLowerCase();for(a=1;a<menus;a++){if(mn==Mname[a])mn=a}return mn;}
function getMenuItem(mn,item){for(a=1;a<mn;a++){menu=eval("menu"+a);item=item+((menu.length-ac)/5)}item--;return item}
function sis(){for(a=1;a<imar.length;a++){tim=imar[a].split("_");if(tim[4]){tim[3]=tim[3]+"_"+tim[4]}menu=eval(window.tim[3]);if(ns4){im=_d.layers[tim[3]].document.layers.eval("el"+tim[2]);imp=gpos(im);eln="";for(x=0;x<2;x++){imo=_d.layers[tim[3]].document.layers.eval(eln+"el"+tim[2]).document.layers[imar[a]];imop=gpos(imo);imL=imp[3]-(imop[3]);imT=(imp[2]/2)-(imop[2]/2);if(menu[15])imL=1;if(menu[13]=="left")imL=1;if(menu[13]=="center")imL=(imp[3]/2);eln="m";spos(imo,imT,imL,null,null);}}else{imo=gmobj(imar[a]);imop=gpos(imo);im=gmobj("el"+tim[2]);imp=gpos(im);if(mac){ssx=menu[6][9];imp[0]=imp[0]-x;imop[0]=imo[0]-x}if(menu[11]){imco=gpos(gmobj("hel"+tim[2]));imc=imco[1];}else{imc=0}imT=imp[0]+(imp[2]/2)-(imop[2]/2);imL=imc+imp[3]-(imop[3]);if(menu[15])imL=imc;if(menu[13]=="left")imL=imc;if(menu[13]=="center")imL=imc+(imp[3]/2);if(ns6&&!ns61){nsC=gpos(gmobj(tim[3]));imT=imT-nsC[0];imL=imL-nsC[1]}spos(imo,imT,imL,null,null);}}}
function fixb(mnu){menu=eval(mnu);ic=(menu.length-ac)/5;menu[21][2]=1;m42=menu[4]*2;mn=gmobj(mnu);if(ns4){if(menu[14]){spos(gmobj("menuback"+mnu),null,null,mn.clip.height+(menu[14]*2),null)}if(menu[11]){mn.clip.width=mn.clip.width+menu[4]}return}mp=gpos(mn);el=menu[21][1];ml=gmobj("el"+(el-1));mlp=gpos(ml);tw=null;th=null;if(menu[11]){lf=0;tc=0;for(y=el-ic;y<el;y++){tc++;ty=gmobj("el"+y);typ=gpos(ty);thy=gmobj("hel"+y);thyp=gpos(thy);if(!ie4){if(thy.parentNode.parentNode.tagName=="TD"){loadWait=1}}else{loadWait=1}if(mac&&!menu[3]){thy.style.width=1+"px";ty.style.width=thy.offsetWidth+menu[6][9]+"px";typ[3]=thy.offsetWidth}if(opera){ty.style.top=menu[4];if(lf==0)lf=menu[4];typ[2]=typ[2]+(m42);spos(ty,null,0,null,typ[3]);}spos(thy,null,lf,typ[2],typ[3]);lfL=0;if(menu[ac-1+(tc*5)]>0)lfL=menu[ac-1+(tc*5)];lf=lf+typ[3]+lfL;hsep=gmobj("hsep"+y);if(y<el-1)spos(hsep,null,typ[3],typ[2]); else spos(hsep,null,0,0,0);}tw=lf-lfL;th=typ[2];if(_d.compatMode=="CSS1Compat"){}else{if(mac){if(!_d.doctype){th=th+(m42);tw=tw+m42}else{if(_d.doctype.name.indexOf(".dtd")<0){th=th+(m42);}}}else if(_d.all&&!opera){tw=tw+(m42);th=th+(m42)}}if(opera)tw=tw+(menu[4])}else{if(ns6)tw=mp[3]-(menu[4]*4);if(opera)tw=menu[3];if(menu[19]){spo=gmobj("S"+mnu);sp=gpos(spo);th=sp[2];}}spos(mn,null,null,th,tw);if(menu[14]){spos(gmobj("menuback"+mnu),null,null,th+(menu[14]*2)+m42,null)}}
function sm(v1){menu=eval(v1);if(menu[19]&&!menu[21][2])fixb(v1);if(opera||ns6||menu[11])if(!menu[7]&&!menu[21][2])fixb(v1);ap=gmobj(v1);
if(flta)if(ap.filters[0])ap.filters[0].Apply();
gmi++;if(!ns4)aps=ap.style; else aps=ap;aps.zIndex=gmi;aps.visibility='visible';if(flta)if(ap.filters[0])ap.filters[0].Play();if(menu[17])SDiv(menu[17],0)}
function popup(mn,mpos){if(isNaN(mn)){for(a=1;a<menus;a++){if(mn==Mname[a])mn=a}}setpos();omv.visibility='hide';clearTimeout(Mtimer);closeallmenus();mtxt='menu'+mn;mi=gmstyle(mtxt);if(ns4)_d.captureEvents(Event.MOUSEMOVE);_d.onmousemove=getMouseXY;mn=gmobj(mtxt);gp=gpos(mn);if(mpos>0)ttop=MouseY+2; else ttop=gp[0];if((ttop+gp[2])>(wh+sy)){ttop=wh-gp[2]+sy;if(!mpos&&mpos>0){spos(mn,ttop,null,null,null);}}if(mpos){spos(mn,ttop,MouseX+2,null,null);}flta=0;if((_d.all&&_d.getElementById)&&!mac){ap=gmobj(mtxt);getflta(ap);}sm(mtxt);am=mn}	
function shl(lyr,el,s){clearTimeout(Mtimer);mt=_d.layers[lyr].document.layers.eval("el"+el);omv.visibility="hide";menu=eval(lyr);mt.visibility=s;omv=mt}
function popi(v1,lyr,el)
{
	kel=el;subfound=0;nv1=v1;
	for(a=1;a<menus;a++){if(v1.substring(10,99)==Mname[a]){v1="show-menu"+a;subfound=1;a=999}}
	if(!om)om=lyr;clearTimeout(Mtimer);	
	if(ParentMenu)ParentMenu.clearTimeout(ParentMenu.Mtimer);		
	if(ns4){timo=_d.layers[lyr].document.layers.eval("el"+el).document.images[0]}else{timo=gmobj("im"+el)}
	o_img="";if (timo){o_img=timo.src;if(SwapIM[el].src)timo.src=SwapIM[el].src};kmnu=lyr;container=eval(lyr);
	if(subfound)
	{
		v1=v1.substring(5,99);
		menu=eval(v1);	
		omnu=v1;
		mi=gmobj(v1);
		gp=gpos(gmobj(lyr));
		h3="";
		if(container[11])h3="h";
		if(ns4){tel=gpos(_d.layers[lyr].document.layers.eval("el"+el))}else{tel=gpos(gmobj(h3+"el"+el))}
		np=gpos(mi);
		if(container[11])
		{
			kmnu=v1
			if(!container[5])container[5]="";
			if(!opera&&_d.all&&!mac)gp[1]=gp[1]+container[4];
			if(ns6)if(ns61)gp[1]=gp[1]+container[4];else gp[1]=-1;gp[0]=gp[0]-container[4]
			if(mac)gp[0]=gp[0]-container[4];
			if(container[5].indexOf("bottom")>=0){tvar=container[1];if(!tvar)tvar=0;gp[0]=gp[0]-np[2]-tel[2]}
			if(tel[1]+gp[1]+np[3]>ww+sx){gp[1]=ww-np[3]-tel[1]+sx;}
			mtop=gp[0]+gp[2];
			mleft=tel[1]+gp[1];
			if(container[15])mleft=mleft-np[3]+tel[3]+(container[4]*2)
			m1=menu[1];
			if(m1){m1=m1+"";if(m1.indexOf("offset=")==0){os=m1.substr(7,99);mtop=(parseInt(os)+mtop);}else{if(m1)mtop=parseInt(m1);}}
			m2=menu[2];
			if(m2){m2=m2+"";
			if(m2.indexOf("offset=")==0){os=menu[2].substr(7,99);mleft=(parseInt(os)+mleft);}else{if(m2)mleft=m2;}
		}
		if(menu[5])setpos();else spos(mi,mtop,mleft-1,null,null);
		}
		else
		{
			if(!ns61&&ns6)tel[0]=tel[0]-gp[0];
			nt=tel[0]+gp[0]+suboffset_top;
			nl=gp[1]+tel[3]-suboffset_left;
			onl=nl
			if((gp[1]+gp[3]+np[3])>=ww||menu[15]){nl=gp[1]-np[3]+suboffset_left}
			if(nl<0)nl=onl
			if(nt+np[2]>wh+sy){nt=wh-np[2]+sy;}
			if(menu[1]>=0)nt=menu[1]
			if(menu[2]>=0)nl=menu[2]
			if(menu[5])setpos();else spos(mi,nt,nl,null,null);
		}
		om+=","+v1;
		if(ShM==1){sm(v1);if(!ns4)mi.style.visibility="visible"}

// Scroll Code **********
		if(menu[19])
		{
			gp=gpos(gmobj(v1));
			gpc=gpos(gmobj("S"+v1))
			tt=0;
			if((gp[0]+gpc[2])>wh+sy)
			{
				spos(gmobj("S"+v1),null,null,(wh+sy)-gp[0]-10-2);
				spos(gmobj(v1),null,null,(wh+sy)-gp[0]-10);
			}
			else
			{
			}
		}
// Scroll Code **********
	}
	else
	{
		frt=v1.indexOf(" ")
		lt=parseLink(v1, "sourceframe");
		if(lt)pf=parent[lt];
		if(frt>0&&v1.indexOf("show-menu=")>-1)
		{
			if(lt)
			{
				v1=v1.substring(10,frt);
				
				if(pf.MLoaded)
				{
					close_el()
					pf.mn=pf.gmobj("menu"+pf.getMenuByName(v1));
					menu=eval(lyr);
					gp=gpos(gmobj(lyr));
					h3="";
					if(container[11])h3="h";
					
					if(ns4){mp=gpos(_d.layers[lyr].document.layers.eval("el"+el))}else{mp=gpos(gmobj(h3+"el"+el))}
					if(menu[11])
					{
						fTop=pf.sy;
						fLeft=gp[1]+mp[1]+Frames_Left_Offset;
					}
					else
					{
						fTop=pf.sy+gp[0]+mp[0]+Frames_Top_Offset;
						fLeft=100
					}
					pf.spos(pf.mn,fTop,fLeft-100,null,null);
					pf.popup(v1);
					pf.ParentMenu=self
				}
			}
		}
		else if(frt>0)
		{
			
			if(lt)
			{
				if(pf)
				{
					v1=v1.substring(10,frt);
					if(pf.MLoaded)pf.closeallmenus();
				}
				
			}
		}
		
	}
	ta=om.split(",")
	strt=0;
	for(a=0;a<ta.length;a++)
	{
		if (ta[a]==v1)strt=0;
		if (strt)
		{
			if(ta[a])
			{
				hll=lyr;
				hel=el;
				close_el();
				tmnu=eval(ta[a])
				if(tmnu[17]&&!menu[17])SDiv(tmnu[17],1)
				SDiv(ta[a],0)
			}
		ta[a]=""
		}
		if(ta[a]==lyr)strt=1;
	}
	mmenu=eval(lyr)
	if(mmenu[7]&&lyr!=ta[0])
	{
		closeallmenus();
		if(v1==ta[ta.length-1])
		{
			if(ns4)shl(lyr,el,'show');
			tmnu=eval(v1)
			if(tmnu[17])SDiv(tmnu[17],0)
			
			SDiv(v1,1)
		}
	}
	om=""
	mdisp=0
	for(a=0;a<ta.length;a++)
	{
		if(ta[a]==lyr)frs=a
		if(ta[a]==v1)fre=a
		if (mdisp||(a>frs&&a<fre))
		{
			if (ta[a]&&ta[a]!=v1)
			{
				SDiv(ta[a],0)
				close_el();
				if(ns4)shl(lyr,el,'hide');
			}
		}
		else
		{
			if(ta[a])om+=ta[a]+",";
		}
		if (v1==ta[a])mdisp=1
	}	
}


// Scroll Code **********
function scroll(val,mnu) 
{
	mn=getMenuByName(mnu);
	mi=gmobj("Smenu"+mn);
	mip=gpos(mi)
	tt=tt-val
	gp=gpos(gmobj("menu"+mn))
	
	if(tt>=0 && tt<(gp[2]-gp[0]))
	{
		mi.style.clip = "rect("+tt+"px "+mip[3]+"px "+(tt+gp[2]-7)+"px 0px)"		
		ns6c=0
		if((ns6&&!ns61)||mac)ns6c=1
		spos(mi,-tt,null,tt+gp[2])
		ST = setTimeout("scroll("+val+",'"+mnu+"')",30);		
	}
	else
	{
		tt=tt+val	
		clearTimeout(ST)
	}
	clearTimeout(Mtimer);
}
// Scroll Code **********


function hl()
{		
	
	arg=hl.arguments;imgact="";hlnk=arg[1];if(!menu[8]&&menu[15])menu[8]="right";am=" "+a;am=am.length-1;subimg="";arg[0]+="";if(ns6){dwd=arg[2][9]*2}else{dwd=0};if(ns4){if(el==0){st="<style type=\"text/css\">A.m{text-decoration:none;}</style>";_d.write(st);oatop=atop;}else{mtl=_d.layers[omnu].document.layers.eval("el"+(el-1));
	if(oatop==atop){atop+=mtl.clip.height}else{atop=menu[4];}oatop=atop}if(menu[11]){atop=menu[4];if(oaleft==aleft){aleft+=mtl.clip.width+arg[8]}else{aleft=menu[4];oaleft=aleft}oaleft=aleft}else{aleft=menu[4];}}
	if(hlnk.substring(0,5)=="show-"){arg[1]=arg[1].toLowerCase();if(arg[7]==hlnk) if(arg[6])arg[7]=arg[6];else arg[7]="";hlnk="#";if (arg[6]+" "!="undefined ")hlnk=arg[6];}
	pI="popi('"+arg[1]+"','"+arg[5]+"',"+el+");";if(arg[1].substring(0,5)=="show-"&&(!menu[11]||menu[6][16])&&(menu[11]||menu[6][10])){im++;ims="im"+im+"_"+a+"_"+el+"_"+arg[5];ti=arg[2][10];if(menu[6][16]&&menu[11])ti=menu[6][16];if(ns4){sti="<img border=0 src="+ti+">";
	subimg="<layer id="+ims+">"+sti+"</layer>";}else{
	
	
	
	if(!parseLink(arg[1],"sourceframe"))imgact="onmouseover=\"gmobj('el"+el+"').onmouseover();\" onmouseout=\"gmobj('el"+el+"').onmouseout();\"";
	subimg="<img id="+ims+" style=\"position:absolute;\" "+imgact+" border=0 src="+ti+">"};imar[im]=ims;}omp=mp;ofc=arg[2][0];ofb=arg[2][1];onc=arg[2][2];onb=arg[2][3];ltarg=" ";lOnfunc="";lOffunc="";ltype="";lalign=menu[8];if(!lalign)lalign="left"
	simg="";bimg="";sbimg="";sbgc="";sbgc=parseLink(hlnk,"separatorcolor");if(!sbgc)if(menu[6][19])sbgc=menu[6][19]; else sbgc=menu[6][4]	
	
	if(hlnk.indexOf(" ")>0){k2o=parseLink(hlnk,"keytoopen");if(k2o){keyar[keyarC]=k2o.toUpperCase()+";"+arg[1]+";"+arg[5]+";"+el;keyarC++;}
		_al=parseLink(hlnk,"align");if(_al)lalign=_al;
		ltarg=parseLink(hlnk,"target");
		if(ltarg)ltarg=" target="+ltarg
		lOnfunc=parseLink(hlnk,"onfunction")+";";
		lOffunc=parseLink(hlnk,"offfunction")+";";
		ltype=parseLink(hlnk,"type");
		tofc=parseLink(hlnk,"offfontcolor");if(tofc)(ofc=cHexColor(tofc))
		tofb=parseLink(hlnk,"offbackcolor");if(tofb)(ofb=cHexColor(tofb))
		tonc=parseLink(hlnk,"onfontcolor");if(tonc)(onc=cHexColor(tonc))
		tonb=parseLink(hlnk,"onbackcolor");if(tonb)(onb=cHexColor(tonb))
		simg=parseLink(hlnk,"swapimage");
		bimg=parseLink(hlnk,"backimage");
		sbimg=parseLink(hlnk,"overbackimage");
		hlnk=parseLink(hlnk,"link");		
	}
	
	if(!ofb&&(mac||opera))ofb="transparent"
	if(arg[7]+""=="undefined"){arg[7]=hlnk}
	if(ltype=="form"){onc=ofc;onb=ofb;mp="default"}
	if(ltype=="header"&&arg[2][17]){ofc=arg[2][17];ofb=arg[2][18];onc=arg[2][17];onb=arg[2][18];mp="default"};smO="ShM=1;";smC="";
	if(menu[16]==1){smO="";hlnk="javascript:ShM=1;"+pI}
	if(hlp[14]){lhrar=location.href.split("/");lhrC1=lhrar[lhrar.length-1];lhrar=hlnk.split("/");lhrC2=lhrar[lhrar.length-1];if(lhrC1!=""&&lhrC1==lhrC2)smc=el;if(hlnk!="#"&&smc==el&&hlp[14]){ofc=arg[2][14];ofb=arg[2][15];}}
	if(ltype=="form"||hlnk=="#"||ltype=="header"){mp="default";
	if(ns4){hlnk="<a ";}else{hlnk=" "}}else{if(ns4){hlnk="<a href=\""+hlnk+"\" "+ltarg}else{hlnk="<a href=\""+hlnk+"\" "+ltarg+" style='text-decoration:none;'>"}}
	if(!ns4){if(bimg)ofb="url("+bimg+") "+ofb;if(sbimg){if(sbimg!="NONE"){onb="url("+sbimg+") "+onb;}}else{if(bimg)onb="url("+bimg+") "+onb;}}
	pd="popdn(this," + el + ", '" + arg[5] + "', '"+arg[1]+"', '"+ofb+"', '"+ofc+"');"
	if(arg[0].indexOf("<img")>=0)
	{
		if(menu[11]&&ns6)ns6hif=1;
		ipos=arg[0].indexOf("<img");
		if(simg)
		{
			SwapIM[el]=new Image();
			SwapIM[el].src=simg
			if(_d.all)
			{
				while(!SwapIM[el].complete)
				{
					SwapIM[el].src=simg
				}
			}

		}
		else
		{
			SwapIM[el]=0
		}
		arg[0]=arg[0].substr(0,ipos+4)+" onclick=\"gmobj('el"+el+"').click()\" id=im"+el+" "+ arg[0].substr(ipos+5,900)}
	if(!isNaN(arg[2][5]))arg[2][5]+="px"
	if(ns4){
		rsp="";
		if(bimg)bimg=" background="+bimg;if(sbimg){if(sbimg!="NONE")sbimg=" background="+sbimg;else sbimg=""}else{sbimg=bimg}
		tdwd=" width="+menu[3]
		if(lalign=="left")tdwd=""
		if (lalign=="right") rsp="<td height=-1></td>"
		ns4nwrap="";if(menu[11])ns4nwrap="nowrap";
		ls="<table border=0 cellpadding="+arg[2][9]+" cellspacing=0><tr><td "+ns4nwrap+" height=-1 align="+lalign+tdwd+">"+hlnk+" style='color:"
		le=";text-decoration:none;font-size:"+arg[2][5]+";font-weight:"+arg[2][7]+";font-family:"+arg[2][8]+"' onmouseover=\"status='"+arg[7]+"';\">"+arg[0]+"</a>"+subimg+"</td>"+rsp+"</tr></table></layer>"
		if(menu[11])nswid=menu[3]; else nswid=(menu[3]-(menu[4]*2))
		lv=" left="+aleft+" width="+nswid+" top="+atop
		nsofb="";if(ofb)nsofb=" BGColor="+ofb
		nsonb="";if(onb)nsonb=" BGColor="+onb
		tshl="shl('"+arg[5]+"',"+el+",'show');"
		if(ltype=="form")tshl=""
		mt="<layer id=mel"+el+lv+" "+nsofb+bimg+" onmouseover=\""+smO+tshl+pI+" "+lOnfunc+";return stch('aa"+arg[7]+"');\">"+ls+ofc+le
		mt+="<layer id=el"+el+lv+" "+nsonb+sbimg+" onmouseover=\"clearTimeout(Mtimer);"+pI+"ltarg='"+ltarg+"'; nshl='"+arg[1]+"'; this.captureEvents(Event.CLICK); this.onClick=dc;\" onmouseout=\""+pd+"; "+lOffunc+"; return stch('');\" visibility=hidden >"+ls+onc+le		
	}else{
		mt="";
		if(menu[11]){bgc=hlp[4];if(hlp[11])bgc=hlp[12];
		mt+="<div id=hel"+el+" style=\"background:'"+ofb+"';width:"+(menu[3]-menu[4])+"px;position:absolute;top:0;left:"+dlft+";\">";dlft=dlft+menu[3]-(menu[4]*2)+menu[4]}		
		rsp=0;if (lalign=="right") rsp=7;		
		mt+=hlnk
		dw=(menu[3]-(2*menu[4]+dwd))
		if(dw+" "=="NaN "){dw=""}else{dw="width:"+dw+"px"}
		if(opera&&menu[3])dw="width:"+(menu[3]-(arg[2][9]*2)-(menu[4]*2))+"px;"
		if(!menu[3]&&ie4)dw="width:1px";
		if(menu[11]&&opera){dw="position:absolute;"}
		if(isNaN(arg[2][9])){padd=arg[2][9]}else{psp=arg[2][9]+"px ";padd=psp+psp+psp+psp}				
		if(menu[11]&&menu[3]>0)dw="position:absolute;width:"+menu[3]+"px"
		hms="";if(menu[11])hms="<div style=\";top:0;position:absolute;\"><div onmouseover=\"clearTimeout(Mtimer)\" id=hsep"+el+" style=\"position:absolute;background:"+sbgc+";width:"+menu[b]+"px; clip:rect(0 "+menu[b]+"px 100% 0);\">\n</div>\n</div>";
		mt+="<div id=el"+el+" style=\"text-align:"+lalign+";"+dw+";padding:"+padd+";font-weight:"+arg[2][7]+";font-style:"+arg[2][6]+";font-family:"+arg[2][8]+";font-size:"+arg[2][5]+";color:"+ofc+";background:"+ofb+";\""
		mt+=" onMouseOver=\"this.style.background='"+onb+"';style.color='"+onc+"';this.style.cursor='"+mp+"';"+smO+"; "+pI+";"+lOnfunc+";return stch('"+arg[7]+"');\""
		mt+=" onMouseOut=\"rep_img(); "+pd+";"+lOffunc+"; return stch('');\">"
		mt+=(arg[0])+"</div>";
		mt+="</a>";
		if (menu[11])mt+=hms+"</div>";
		mt+=subimg
	}
	mp=omp
	_d.write(mt);el++;
}


function dmenu(mnu){
	menu=eval(mnu);menu[21]=new Array();if(menu[7]){parr[parr.length]=mnu.substr(4,3);}
	atop=menu[4];if(!atop)atop=0;hlp=menu[6];if(!menu[4])menu[4]=0;if(ns4)eq="="; else eq=":";
	for(z=0;z<21;z++){if(z==5)z=12;if(z==16)z++;if(hlp[z]&&hlp[z].charAt(0)!="#")hlp[z]=cHexColor(hlp[z]);}	
	if(menu[14]){m14=";"+menu[14];m14ar=m14.split(";");if(m14ar[2]){m14h=m14ar[1];m14w=m14ar[2].substr(6,99)+"px";menu[14]=m14h}else{m14h=m14ar[1];m14w="100%"}
	if(_d.layers)_d.write("<layer id=menuback"+mnu+" top="+(menu[1]-m14h)+" height=2 width="+m14w+" left=0 bgcolor="+hlp[1]+"></layer>");else	_d.write("<div id=menuback"+mnu+" style=\"position:absolute;top:"+(menu[1]-m14h)+"px;width:"+m14w+";height:2px;background:"+hlp[1]+"\">&nbsp;</div>")}
	if(el)ns6c=3;else ns6c=2;ns6w=0;if(menu[8]=="right"&&ns6)ns6w=7;
	if (menu[3]<1)menu[3]="undefined"
	if(ns4)
	{
		bgc="";thw=menu[3];
		if(menu[11]){ic=(menu.length-ac)/5;thw=(menu[3]*ic)-(menu[4]*ic)+menu[4]}
		if(hlp[4])bgc="bgColor="+hlp[4]
		if (hlp[11])bgc="bgColor="+hlp[13]
		m18="";if(menu[18])m18="background="+menu[18];
		mt="<layer "+m18+" z-index=19 visibility=hidden "+bgc+" id="+mnu+" top="+menu[1]+" left="+menu[2]+" width="+thw+">"
		if (hlp[11])mt+="<layer bgcolor="+hlp[12]+" top=0 left=0 height=2 width=100%></layer>"
		mt+="<layer id="+mnu+"3d bgcolor="+hlp[12]+" top=0 left=0 height=0 width=2></layer>"
	}
	else
	{
		m18="";if(menu[18]){m18="url("+menu[18]+ ") ";}
		iedf="";if(!opera&&!mac&&_d.all&&menu[9]){iedf=" filter:";if(navigator.appVersion.indexOf("MSIE 5.5")>0){menu[9]=menu[9].substr(0,menu[9].indexOf(";"))}flt=menu[9].split("\;");
		for(fx=0;fx<flt.length;fx++){iedf+=" progid:DXImageTransform.Microsoft."+flt[fx]}}
		if(!hlp[4]&&(mac||opera))hlp[4]="transparent"
		if(hlp[11])brd = "border:solid "+menu[4]+"px; border-left-color:"+hlp[12]+";border-top-color:"+hlp[12]+";border-bottom-color:"+hlp[13]+";border-right-color:"+hlp[13]+";";else brd = " border:solid "+menu[4]+"px "+hlp[4]+";";
		mt="";
		scrof=""
		if(!ns6)scrof="overflow:hidden;";
		mt+="<div id="+mnu+" style=\""+iedf+scrof+";z-index:19;visibility:hidden;"+brd+"position:absolute;background:"+m18+hlp[1]+";width:"+(menu[3]+ns6w)+"px;left:"+menu[2]+"px;top:"+menu[1]+"px;\">"

if(menu[19]){mt+="<div id=S"+mnu+" style=\"position:absolute;overflow:hidden;\">"}
	}
	
	_d.write(mt)
	x=0;
	dlft=0
	aleft=0;
	if(menu[3]&&menu[11]&&(ns6||opera))menu[3]=menu[3]-(hlp[9]*2)
	for(b=ac;b<menu.length;b++)
	{
		b=b+4
		if(menu[b-3]==""){menu[b-3]="#"}
		menu[b-3]=menu[b-3].replace(/\"/gi, "&quot;")
		if(menu[b-2])menu[b-2]=menu[b-2].replace(/\"/gi, "&quot;")
		hl(menu[b-4], menu[b-3],hlp,100,100,mnu,menu[b-2],menu[b-1],menu[b-5])
		txt="";
		if(ns4)
		{
			mt=_d.layers[mnu].document.layers.eval("el"+(el-1))
			if (!menu[11])
			{
				if (menu[b]&&b<menu.length-1)
				{
					
					x=atop+mt.clip.height
					if(sbgc)bgc="bgcolor="+sbgc;
					if(!hlp[11])x++
					txt="<layer top="+(x-2)+" left="+menu[4]+" height="+menu[b]+" "+bgc+" width="+mt.clip.width+" onmouseover=\"clearTimeout(Mtimer)\" ></layer>"
					if(hlp[11]){txt+="<layer top="+(x-1)+" left="+menu[4]+" height=1 bgcolor="+hlp[12]+" width="+mt.clip.width+"></layer>"
					}
				}
			}
			else
			{
				if(sbgc)txt="<layer bgcolor="+sbgc+" left="+(aleft+mt.clip.width)+" width="+menu[b]+" top="+x+" height="+(mt.clip.height+(menu[4]))+"></layer>"	
			}
		} 
		else	
		{			
			mbw=menu[b];
			if(hlp[19])bc=hlp[19]; else	bc=hlp[4]				
			if(b+1==menu.length)mbw=0;
			if(mbw>0)
			{				
				if(!sbgc)sbgc=bc;				
				if (!menu[11])
				{
					dwid=(menu[3]-menu[4]*2)
					if((_d.all&&_d.compatMode=="CSS1Compat")||(mac&&_d.doctype&&_d.doctype.name.indexOf(".dtd")!=-1))dwid=menu[3]+(menu[6][9]*2)-(menu[4]*2)
					if (hlp[11]&&mbw==1)
					{
						oc="";if(opera)oc="_d.getElementById('el"+el+"').style.background='"+hlp[1]+"';_d.getElementById('el"+el+"').style.color='"+hlp[0]+"'";
						txt="<div onmouseout=\""+oc+"\" onmouseover=\"clearTimeout(Mtimer)\" style=\"position:relative\">"
						txt+="<div style=\"border-top:1px solid "+hlp[13]+"; background:"+hlp[12]+"; width:"+dwid+"px;position:absolute;clip:rect(0 100% 2px 0);height:1px;\">"
						txt+="</div>\n</div>"
					}
					else
					{
						txt="<div>";
						txt+="<div onmouseout=\"popdn();\" onmouseover=\"clearTimeout(Mtimer)\" style=\"background:"+sbgc+";width:"+dwid+"px;position:absolute;"
						txt+="clip:rect(0 100% "+mbw+"px 0);height:"+mbw+"px;\">"
						txt+="</div>"
						txt+="</div>"
					}
				}
			}			
		}
		_d.write(txt);
		omnu=mnu;
		mt=""
	}
	oatop=-1;
	if (ns4){mt="</layer>"}else{
	mt+="</div>\n";
	if(menu[19])mt+="</div>"}
	_d.write(mt)
	if(ns4){ml=gmobj(mnu);ml.clip.height += menu[4];ml3d=_d.layers[mnu].document.layers.eval(mnu+"3d");
	if(hlp[11])ml3d.clip.height=ml.clip.height};
	menu[21][1]=el
}
function checs(e){stopchec=1;for(a=1;a<menus;a++){menu=eval("menu"+a);if(menu[10]>0){stopchec=0;if(df!=0||osy!=sy){mi=gmobj("menu"+a);tm=gpos(mi);mit=tm[0];if(isNaN(menu[1]))menu[1]=0;df=parseInt(menu[1])-mit;if(sy+menu[10]>=menu[1])df=sy-mit+menu[10];nt=df/followspeed;of1=nt.toString();ofr=of1.split("\.");if(ofr[1]+" "=="undefined ")ofr[1]=0;ofrac=ofrac+parseInt(ofr[1]);if(mac&&parseInt(nt)==0){clearTimeout(pu);return;}if(ofrac>10){ofrac=ofrac-10;if(df>=0)nt++; else	nt--;}spos(mi,mit+nt,null,null,null);if(menu[14]){spos(gmobj("menubackmenu"+a),mit+nt-menu[14],null,null,null)}}else{clearTimeout(pu);return;}osy=sy}}if(stopchec){osy=sy;return}pu=setTimeout('checs()',followrate);}
function PlaceMenu(mn){if(ns4||ns6)pos="relative";else pos="absolute";mnt="<div id=\""+mn+"DIV\" style=\"width:1;height:1;position:"+pos+"\"></div>";menu=eval("menu"+getMenuByName(mn));menu[21][0]=mn+"DIV";_d.write(mnt)}
function setpos(){for(a=1;a<menus;a++){menu=eval("menu"+a);if(menu[21][0]){gm=gmobj(menu[21][0]);if(ns4){ntp=gm.pageY;nlp=gm.pageX}else{gp=gpos(gm);ntp=gp[0];nlp=gp[1]}mn=gmobj("menu"+a);mnp=gpos(mn);if(ntp==0)ntp=null;if(nlp==0)nlp=null;spos(mn,ntp,nlp,null,null);}else{if(!menu[5])menu[5]="";if (menu[5].indexOf("left")!=-1||menu[5].indexOf("center")!=-1||menu[5].indexOf("right")!=-1){mn=gmobj("menu"+a);mnp=gpos(mn);mnw=mnp[3];nlp=((ww-mnw)/2);if(menu[5].indexOf("right")!=-1)nlp=ww-mnw;if(menu[5].indexOf("left")!=-1)nlp=1;ts=menu[2]+"";if(ts.indexOf("offset=")==0){os=ts.substr(7,99);nlp=nlp+parseInt(os)}spos(mn,null,nlp,null,null)}if(menu[5].indexOf("top")!=-1||menu[5].indexOf("bottom")!=-1||menu[5].indexOf("middle")!=-1){mn=gmobj("menu"+a);mnp=gpos(mn);mnh=mnp[2];if(opera)mnh=mn.style.pixelHeight;nlp=wh-mnh;if(menu[5].indexOf("middle")!=-1)nlp=((wh-mnh)/2);if(menu[5].indexOf("top")!=-1)nlp=1;ts=menu[1]+"";if(ts.indexOf("offset=")==0){os=ts.substr(7,99);mnp=gpos(mn);nlp=nlp+parseInt(os)}if(menu[10]){menu[1]=nlp;nlp=nlp+sy}spos(mn,nlp,null,null,null)}}}}
function MScan(){sy=self.pageYOffset;sx=self.pageXOffset;if(opera){ww=window.innerWidth;wh=window.innerHeight}else if(_d.all){sy=_d.body.scrollTop;sx=_d.body.scrollLeft;if(sy==0&&!ie4&&!mac)sy=_d.documentElement.scrollTop;if(_d.compatMode=="CSS1Compat"){ww=document.documentElement.clientWidth;wh=document.documentElement.clientHeight}else{ww=_d.body.clientWidth;wh=_d.body.clientHeight}}else{if(ns4){ww=self.innerWidth-16;wh=self.innerHeight-17}else{ww=_d.body.offsetWidth;if(!ww)ww=self.innerWidth-15;ww--;wh=self.innerHeight;}}if(opera&&(owh!=wh||oww!=ww)){if(MLoaded>1){for(ax=1;ax<menus;ax++){fixb("menu"+ax);if(menu[7])popup(ax);sis();}MLoaded--;}MLoaded++;oww=ww;owh=wh;}if(sy!=osy)checs();setpos();}
function mOL(){if(!ns61&&ns6||ns6hif){Minit()}MLoaded=1;if(Oload)Oload();if(loadWait){for(x=0;x<parr.length;x++){fixb("menu"+parr[x]);popup(parr[x]);}}sis();MScan();}
function Minit(){el=0;df=-1;if((_d.all&&_d.getElementById)&&!mac)_d.write("<script>function getflta(ap){try{if(ap.filters){flta=1}}catch(e){}}</script>");for(x=0;x<parr.length;x++){popup(parr[x]);fixb("menu"+parr[x])}if(ns4){_d.captureEvents(Event.MOUSEMOVE);oww=self.innerWidth-16;owh=self.innerHeight-17;window.onresize=function(){if(ww!=oww||wh!=owh)window.history.go(0)}}_d.onmousemove=getMouseXY;MScan();}
for(a=1;a<menus;a++){dmenu("menu"+a)}
if(mac45)Oload=window.onLoad;else Oload=window.onload;
if(!ns61&&ns6||ns6hif){window.onload=mOL}else{Minit();sis();window.onload=mOL}
setInterval("MScan()",250);
