<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	 
	String ctx = request.getContextPath() ;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "DTD/xhtml1-transitional.dtd">
<html>
<head> 
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style type="text/css"><!--/* Licensed under the Apache License, v. 2.0 (http: //www.apache.org/licenses/)*/.g-doc{width: 100%;text-align: left}.g-section{width: 100%;vertical-align: top;display: inline-block}*: first-child+html .g-section{display: block}* html .g-section{overflow: hidden}@-moz-document url-prefix (){.g-section{overflow: hidden}}@-moz-document url-prefix (){.g-section , tt: default{overflow : visible}}.g-doc-1024{width: 73.074em;*width: 71.313em;min-width: 950px;margin: 0 auto;text-align: left}.g-doc-800{width: 57.69em;*width: 56.3em;min-width: 750px;margin: 0 auto;text-align: left}.g-section: after{content: ".";display: block;height: 0;clear: both;visibility: hidden}.g-section,.g-unit{zoom: 1}.g-split .g-unit{text-align: right}.g-split .g-first{text-align: left}.g-tpl-160 .g-unit,.g-unit .g-tpl-160 .g-unit,.g-unit .g-unit .g-tpl-160 .g-unit{margin: 0 0 0 160px;width: auto;float: none}.g-unit .g-unit .g-tpl-160 .g-first,.g-unit .g-tpl-160 .g-first,.g-tpl-160 .g-first{margin: 0;width: 160px;float: left}.g-tpl-160-alt .g-unit,.g-unit .g-tpl-160-alt .g-unit,.g-unit .g-unit .g-tpl-160-alt .g-unit{margin: 0 160px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-160-alt .g-first,.g-unit .g-tpl-160-alt .g-first,.g-tpl-160-alt .g-first{margin: 0;width: 160px;float: right}.g-tpl-180 .g-unit,.g-unit .g-tpl-180 .g-unit,.g-unit .g-unit .g-tpl-180 .g-unit{margin: 0 0 0 180px;width: auto;float: none}.g-unit .g-unit .g-tpl-180 .g-first,.g-unit .g-tpl-180 .g-first,.g-tpl-180 .g-first{margin: 0;width: 180px;float: left}.g-tpl-180-alt .g-unit,.g-unit .g-tpl-180-alt .g-unit,.g-unit .g-unit .g-tpl-180-alt .g-unit{margin: 0 180px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-180-alt .g-first,.g-unit .g-tpl-180-alt .g-first,.g-tpl-180-alt .g-first{margin: 0;width: 180px;float: right}.g-tpl-200 .g-unit,.g-unit .g-tpl-200 .g-unit,.g-unit .g-unit .g-tpl-200 .g-unit{margin: 0 0 0 200px;width: auto;float: none}.g-unit .g-unit .g-tpl-200 .g-first,.g-unit .g-tpl-200 .g-first,.g-tpl-200 .g-first{margin: 0;width: 200px;float: left}.g-tpl-225 .g-unit,.g-unit .g-tpl-225 .g-unit,.g-unit .g-unit .g-tpl-225 .g-unit{margin: 0 0 0 226px;width: auto;float: none}.g-unit .g-unit .g-tpl-225 .g-first,.g-unit .g-tpl-225 .g-first,.g-tpl-225 .g-first{margin: 0;width: 226px;float: left}.g-tpl-250 .g-unit,.g-unit .g-tpl-250 .g-unit,.g-unit .g-unit .g-tpl-250 .g-unit{margin: 0 0 0 250px;width: auto;float: none}.g-unit .g-unit .g-tpl-250 .g-first,.g-unit .g-tpl-250 .g-first,.g-tpl-250 .g-first{margin: 0;width: 250px;float: left}.g-tpl-250-alt .g-unit,.g-unit .g-tpl-250-alt .g-unit,.g-unit .g-unit .g-tpl-250-alt .g-unit{margin: 0 250px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-250-alt .g-first,.g-unit .g-tpl-250-alt .g-first,.g-tpl-250-alt .g-first{margin: 0;width: 250px;float: right}.g-tpl-300 .g-unit,.g-unit .g-tpl-300 .g-unit,.g-unit .g-unit .g-tpl-300 .g-unit{margin: 0 0 0 300px;width: auto;float: none}.g-unit .g-unit .g-tpl-300 .g-first,.g-unit .g-tpl-300 .g-first,.g-tpl-300 .g-first{margin: 0;width: 300px;float: left}.g-tpl-300-alt .g-unit,.g-unit .g-tpl-300-alt .g-unit,.g-unit .g-unit .g-tpl-300-alt .g-unit{margin: 0 300px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-300-alt .g-first,.g-unit .g-tpl-300-alt .g-first,.g-tpl-300-alt .g-first{margin: 0;width: 300px;float: right}.g-tpl-25-75 .g-unit,.g-unit .g-tpl-25-75 .g-unit,.g-unit .g-unit .g-tpl-25-75 .g-unit,.g-unit .g-unit .g-unit .g-tpl-25-75 .g-unit{width: 75%;float: right;margin: 0}.g-tpl-25-75-alt .g-unit,.g-unit .g-tpl-25-75-alt .g-unit,.g-unit .g-unit .g-tpl-25-75-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-25-75-alt .g-unit{width: 25%;float: left;margin: 0}.g-tpl-75-25-alt .g-unit,.g-unit .g-tpl-75-25-alt .g-unit,.g-unit .g-unit .g-tpl-75-25-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-75-25-alt .g-unit{width: 75%;float: left;margin: 0}.g-tpl-75-25 .g-unit,.g-unit .g-tpl-75-25 .g-unit,.g-unit .g-unit .g-tpl-75-25 .g-unit,.g-unit .g-unit .g-unit .g-tpl-75-25 .g-unit{width: 25%;float: right;margin: 0}.g-tpl-33-67 .g-unit,.g-unit .g-tpl-33-67 .g-unit,.g-unit .g-unit .g-tpl-33-67 .g-unit,.g-unit .g-unit .g-unit .g-tpl-33-67 .g-unit{width: 67%;float: right;margin: 0}.g-tpl-33-67-alt .g-unit,.g-unit .g-tpl-33-67-alt .g-unit,.g-unit .g-unit .g-tpl-33-67-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-33-67-alt .g-unit{width: 33%;float: left;margin: 0}.g-tpl-67-33-alt .g-unit,.g-unit .g-tpl-67-33-alt .g-unit,.g-unit .g-unit .g-tpl-67-33-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-67-33-alt .g-unit{width: 67%;float: left;margin: 0}.g-tpl-67-33 .g-unit,.g-unit .g-tpl-67-33 .g-unit,.g-unit .g-unit .g-tpl-67-33 .g-unit,.g-unit .g-unit .g-unit .g-tpl-67-33 .g-unit{width: 33%;float: right;margin: 0}.g-tpl-50-50 .g-unit,.g-unit .g-tpl-50-50 .g-unit,.g-unit .g-unit .g-tpl-50-50 .g-unit,.g-unit .g-unit .g-unit .g-tpl-50-50 .g-unit{width: 50%;float: right;margin: 0}.g-tpl-50-50-alt .g-unit,.g-unit .g-tpl-50-50-alt .g-unit,.g-unit .g-unit .g-tpl-50-50-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-50-50-alt .g-unit{width: 50%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-tpl-75-25 .g-first,.g-tpl-25-75 .g-first{width: 25%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-25-75-alt .g-first,.g-unit .g-unit .g-tpl-25-75-alt .g-first,.g-unit .g-tpl-25-75-alt .g-first,.g-tpl-25-75-alt .g-first{width: 75%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25-alt .g-first,.g-unit .g-unit .g-tpl-75-25-alt .g-first,.g-unit .g-tpl-75-25-alt .g-first,.g-tpl-75-25-alt .g-first{width: 25%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-tpl-75-25 .g-first,.g-tpl-75-25 .g-first{width: 75%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-33-67 .g-first,.g-unit .g-unit .g-tpl-33-67 .g-first,.g-unit .g-tpl-33-67 .g-first,.g-tpl-33-67 .g-first{width: 33%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-33-67-alt .g-first,.g-unit .g-unit .g-tpl-33-67-alt .g-first,.g-unit .g-tpl-33-67-alt .g-first,.g-tpl-33-67-alt .g-first{width: 67%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-67-33-alt .g-first,.g-unit .g-unit .g-tpl-67-33-alt .g-first,.g-unit .g-tpl-67-33-alt .g-first,.g-tpl-67-33-alt .g-first{width: 33%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-67-33 .g-first,.g-unit .g-unit .g-tpl-67-33 .g-first,.g-unit .g-tpl-67-33 .g-first,.g-tpl-67-33 .g-first{width: 67%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-50-50 .g-first,.g-unit .g-unit .g-tpl-50-50 .g-first,.g-unit .g-tpl-50-50 .g-first,.g-tpl-50-50 .g-first{width: 50%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-50-50-alt .g-first,.g-unit .g-unit .g-tpl-50-50-alt .g-first,.g-unit .g-tpl-50-50-alt .g-first,.g-tpl-50-50-alt .g-first{width: 50%;float: right;margin: 0}.g-tpl-nest{width: auto}.g-tpl-nest .g-section{display: inline}.g-tpl-nest .g-unit,.g-unit .g-tpl-nest .g-unit,.g-unit .g-unit .g-tpl-nest .g-unit,.g-unit .g-unit .g-unit .g-tpl-nest .g-unit{float: left;width: auto;margin: 0}.g-tpl-nest-alt .g-unit,.g-unit .g-tpl-nest-alt .g-unit,.g-unit .g-unit .g-tpl-nest-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-nest-alt .g-unit{float: right;width: auto;margin: 0}@media screen , projection , print{html,body{height: auto;margin: 0;padding: 0}body{font-family: Helvetica, Arial, sans-serif;font-size: small;color: #000;background-color: #fff;margin: 3px 8px}a,a: link{color: #00c}a: active{color: red}a: visited{color: #551a8b}p{padding: 1em 0 0 0;margin: 0;line-height: 125%}img{border: none;padding: 0;margin: 0}a img{border: 1px solid;padding: 1px}code,pre{font-family: monospace;color: #007000}code{font-size: 10pt}pre{font-size: 9pt;background-color: #fafafa;border: 1px solid #bbb;line-height: 125%;margin: 1em 0 0 0;padding: .99em;overflow: auto;word-wrap: break-word}form{margin: 1em 0 0 0;padding: 0}blockquote{text-align: justify;margin: 1em 90px 0 70px;padding: 0 20px}hr{border: 1px solid;border-color: #bbb;margin: 1em 0}h1,h2,h3,h4{margin-bottom: 0}h1{font-size: 160%}h2{font-size: 140%}h3{font-size: 120%}h4{font-size: 110%}#gc-pagecontent h1{font-size: 130%;font-weight: bold;margin: 2em 0 0 -10px;padding: 1px 3px;position: relative;border-top: 1px solid #36C;background-color: #e5ecf9}.labs #gc-pagecontent h1{background-color: #dcf6db;border-top: 1px solid #090}.depr #gc-pagecontent h1{background-color: #ddd;border-top: 1px solid #333}#gc-pagecontent h2{font-size: 130%;font-weight: bold;margin: 1.5em 0 0 0}#gc-pagecontent h3{font-size: 110%;margin: .7em 0 0 0;position: relative;top: .4em}#gc-pagecontent h4{font-size: 100%;font-weight: bold;margin: .6em 0 0 0;position: relative;top: .4em;z-index: 5}#gc-pagecontent h5{font-size: 100%;font-weight: normal;font-style: italic;text-decoration: underline;margin: .4em 0 0 0;position: relative;top: .4em;z-index: 5}#gc-pagecontent h1.page_title,#gc-pagecontent h2.page_title{line-height: 130%;font-size: 170%;margin: 0 0 0 -10px;padding: .8em 0 0;border: none;background: none}ol,ul{padding: 0;margin: .5em 0 0 15px;line-height: 125%}li ol,li ul{padding: 0;margin: 0 0 0 10px}li{margin: .3em 0 0 1.5em;padding: 0}dl{margin: 0;padding: 0;line-height: 125%}dt{font-weight: bold;margin: .75em 0 0 0;padding: 0}dd{margin: .4em 0 0 2em;padding: 0;font-weight: normal}li pre{margin: .5em 0 .6em 0}li p,dd p{padding: .5em 0 .6em 0;margin: 0}ol.alpha{list-style: lower-alpha}ol.alphacap{list-style: upper-alpha}ol.roman{list-style: lower-roman}ol.romancap{list-style: upper-roman}ol.termslist{list-style-type: decimal}ol.termslist>li>ol{list-style-type: lower-alpha}ol.termslist>li>ol>li>ol{list-style-type: lower-roman}.tablelist{margin: 0 0 1em 0}.listhead li{font-weight: bold}.listhead li *,.listhead li li{font-weight: normal}.code li{font-family: monospace;font-size: 10pt}.code li p,.code li li{font-family: Arial, Helvetica, sans-serif}li p.note,li p.warning,li p.labswarning,li p.caution{margin: .8em 0 0 0;padding: .2em .5em .2em .9em}ol.toc,div.toc ol{margin: 1em 0 0 0;padding: 0;list-style: none}ol.toc li,div.toc ol li{font-weight: bold;margin: .5em 0 0 1.5em;padding: 0}ol.toc li ol,div.toc ol li ol{margin: 0;padding: 0}ol.toc li ol li,div.toc ol li ol li{padding: 0;margin: .1em 0 0 1em;font-weight: normal;list-style: none}table ol.toc{margin-left: 0}table{border-collapse: collapse;line-height: 125%}th,td{text-align: left;vertical-align: top}table{margin: 1em 0 0 1px;border: 1px solid;border-color: #bbb;border-spacing: 0;border-collapse: collapse;clear: right}th{font-weight: bold;text-align: left;padding: 6px 12px;border: 1px solid #bbb;background-color: #e5ecf9}.labs #gc-pagecontent th{background-color: #dcf6db}td{padding: 6px 12px;border: 1px solid #bbb;background-color: #fff;text-align: left;vertical-align: top}td h1,td h2,td h3,td h4,td h5{margin-left: 0}td ul,td ol{margin: 0 0 1em 15px;padding: 0}td p,td dl{margin: 0 0 1em 0;padding: 0}td *+p{margin: 1em 0 0 0;padding: 0}tr.alt td,td.alt{background: #f5f5f5}table.columns{border: none;margin: 1em 0 0}table.columns td{border: none;padding: 0 3em 0 0}table.columns td p{margin: 1em 0 0 0;padding: 0}.ftdpartners{border: none;border-top: 1px solid #c9d7f1;margin-top: 2em}.ftdpartners td{padding: 0;text-align: center;border: none;vertical-align: middle}.ftdpartners .ftdpartners-col{width: 30%;text-align: left}.deprecated,.deprecated *,.deprecated a: link,.deprecated a: hover,.deprecated a: visited{color: #9b9b9b}.grey{color: #666}.blue{color: #36e}.iconlist{margin-left: 0}.iconlist li{padding: 0 0 0 50px;margin: 0 0 30px 0;list-style: none}.topmargin{margin-top: 1em !important}.nomargin{margin: 0 !important}.nopadding{padding: 0 !important}.noborder{border: none !important}.border{border: 1px solid gray;padding: 1px}.normalsize{font-size: 100% !important}.nolist li{margin-left: 0;list-style: none}.noindent{margin-left: 0}.doublespace,.doublespace li{margin-top: 1em}ul.doublespace,ol.doublespace{margin-top: 0;padding-top: 0}.terms,.termsbox{margin: 1em 0 0 0;padding: .5em;background-color: #efefef}.terms form,.termsbox form{margin: 0;padding: 0}.terms table,.termsbox table,.terms td,.termsbox td{border: none;background-color: transparent}div.row{float: left;clear: left;position: relative;margin: 1em 0 0;padding: 0}.left{float: left;margin: 0 1em 0 0;padding: 0}div.special{border: 1px solid;padding: 0 .9em .9em;margin: 1em 0 0 0;background-color: #e5ecf9;border-color: #36C}.labs div.special{background-color: #dcf6db;border: 1px solid #090}p.note,p.caution,p.warning{margin: 1em 0 0 0;padding: .2em .5em .2em .9em;background-color: #efefef;border-top: 1px solid;border-bottom: 1px solid}p.labswarning{margin: 1em 0 0 0;padding: .2em .5em .2em 20px;background: url(/images/labs-11.png) top left no-repeat;background-position: 4px 6px;background-color: #dcf6db;border-top: 1px solid;border-bottom: 1px solid;border-color: #090}p.note{border-color: #bbb}p.caution{border-color: #fc3}p.warning{border-color: #a03}p.warning b,p.warning em,p.warning strong{color: #a03;font-weight: bold}p.caption{padding: 1px 0 0;margin: 0}div.linkbox{margin: 1em 25px 1em 0;padding: .5em 0}div.linkbox a{font-size: 100%;font-weight: bold;border: 1px solid;margin: 0;padding: .5em;background-color: #e5ecf9;border-color: #36C}.promo{margin: 1em 1em 0 0;padding: 0 1em 1em;border: 1px solid #36C}.new{font-size: 78%;font-weight: bold;color: red;text-decoration: none}.subscribe{float: right}.subscribe a,.subscribe a: visited{text-decoration: none;color: #bb4900}.subscribe a .linktext{text-decoration: underline}.subscribe img{width: 15px;height: 15px;vertical-align: middle;padding-bottom: 2px}p#date,p.date{text-align: left;margin: 0;padding: 1em;font-style: italic;font-size: x-small}p.backtotop{float: right;margin: .5em 0 0 4em;position: relative;padding: 0;font-size: x-small;z-index: 99}div.sidebox{width: 22em;margin: 1em 0 0 20px;padding: 0 0 1em 1em;border-left: 1px dotted silver;float: right;clear: right;position: relative;background-color: #fff;position: relative;z-index: 100}div.sidebox h2{font-size: 110%;font-weight: bold;margin: 0 5px 0 -5px;padding: .1em 3px .1em 5px;background-color: #e5ecf9;border-color: #36C}.hidden,.hidden *,#skipto,#skipto *,#skipto a,#skipto a: hover,#skipto a: visited{position: relative;top: -9999px;left: -9999px;height: 0;width: 0;overflow: hidden;z-index: 4444}#skipto,#skipto *,#skipto a,#skipto a: hover,#skipto a: visited{_position: relative}#skipto a: focus{position: fixed;top: 5px;left: 5px;height: auto;width: auto;background-color: #fff;border: 2px solid #00C;font-weight: bold;padding: 5px}#gc-container{margin: 0;padding: 0;max-width: 1160px}#gc-pagecontent,#gc-home{position: relative}#gc-pagecontent{padding-left: 24px;/*border-left: 3px solid #e5ecf9*/}#deprecatewarn{background: #ff8;margin-bottom: -20px;text-align: center;padding: 3px 1px 3px 1px}#gb{width: 100%;padding: 3px 0 0 0;text-align: right}#gb a{text-decoration: none;color: #00c}img.globeicon{border: medium none;vertical-align: bottom;padding: 0;margin-right: 1px;margin-top: 2px}.gbh{border-top: 1px solid #c9d7f1;font-size: 1px;height: 0;position: absolute;top: 24px;width: 100%}#langwarn{text-align: right;font-size: 80%;margin-bottom: -13px;color: #666}.moreright{margin: 2px 0 2px 0;text-align: right}#gc-header{padding: 7px 0 0 0;margin: 0px ;}#gc-header #logo{margin: 0;padding-top: 5px;position: absolute}#gc-header a img{border: none;padding: 0;margin: 0}#gc-header #gc-logo-img{background-image: url(/images/sprites08132008.png);background-position: -28px -36px;width: 153px;height: 55px;cursor: pointer}#codesiteContent{margin-top: 3px;padding: 3px}#gc-topnav{font-size: 1em;margin: 0;padding: .1em 0;width: 100%;white-space: nowrap;word-wrap: normal;background-color: #e5ecf9;border-top: 1px solid #36C}.labs #gc-topnav{background-color: #dcf6db;border-top: 1px solid #090}.depr #gc-topnav{background-color: #ddd;border-top: 1px solid #333}#gc-topnav h1{font-size: 1.5em;line-height: 1.3em;font-weight: bold;background-color: transparent;border: 0;margin: 0;padding: 0 0 0 26px;float: left}#gc-topnav h1.msie{padding-left: 0}#gc-topnav h1.mozilla{padding-left: 1px}#gc-topnav h1.default{padding-left: 2px}#gc-topnav img{padding: .3em .3em .3em .5em;float: left;cursor: pointer}#gc-topnav ul{line-height: 1em;text-align: right;list-style: none;margin: 0;padding: .47em 0}#gc-topnav ul li{float: none;display: inline;margin: 0;padding: 0}#gc-topnav li a{padding: 0 .8em}.cs-breadcrumbs{list-style: none;margin: 0 0 0 -20px;padding: .5em 0 0 10px;position: relative}.cs-breadcrumbs li{display: inline;padding: 0 0 0 0;margin: 0}a.selected,.selected a,a.selected: visited,.selected a: visited{color: #000;text-decoration: none}#gc-topnav a.selected,#gc-topnav a.selected: visited{font-weight: bold}#gc-toc{clear: left;padding: .5em 0 0 0 !important;margin-bottom: 1em;height: auto}#gc-toc *{zoom: 1}#gc-toc ul,#gc-toc ol{padding: .6em 0 0;margin: 0;line-height: 120%}#gc-toc ul ul,#gc-toc ol ol{padding: 0;margin: 0}#gc-toc ul *,#gc-toc ol *,.treelist *{vertical-align: middle}#gc-toc li a{padding-right: 4px;_padding-right: 0}#gc-toc ul li,#gc-toc ol li,#gc-toc .treelist li{list-style: none;padding: .2em 0 .2em 2px;margin: 0}#gc-toc ul li li,#gc-toc ol li li,#gc-toc .treelist li li{padding: .25em 0 .25em 15px}.treelist li h2{display: inline}#gc-toc ul h1,#gc-toc ol h1,#gc-toc ul h2,#gc-toc ol h2{font-weight: bold;font-size: 100%;margin: 0;padding: .2em 0;border: none;background: #fff}#gc-toc ul .selected,#gc-toc ol .selected,.treelist .selected{background-color: #e5ecf9;color: #000;text-decoration: none;z-index: 2;position: relative}.labs #gc-toc ul .selected,.labs #gc-toc ol .selected,.labs .treelist .selected{background-color: #dcf6db}.depr #gc-toc ul .selected,.depr #gc-toc ol .selected,.depr .treelist .selected{background-color: #ddd}#gc-toc ul div.tlw-title,#gc-toc ol div.tlw-title,#gc-toc .treelist div.tlw-title{position: relative;margin: 0 0 0 -15px;padding: .2em 0 .2em 15px}.treelist .tlw-hidden{display: none}.treelist div.tlw-title{position: relative;margin-left: -15px;padding-left: 15px}.treelist div.tlw-title a.tlw-control{padding: 0;margin: 0 0 0 -12px;overflow: hidden;background-color: transparent}#gc-toc ul div.tlw-title img.tlw-control,#gc-toc ol div.tlw-title img.tlw-control,.treelist div.tlw-title img.tlw-control{position: relative;height: inherit;width: 9px;margin: 0 0 3px !important;padding: 0;border: none}#gc-toc ul .tlw-plus,#gc-toc ol .tlw-plus,.treelist .tlw-plus{background-image: url(/images/sprites08132008.png);background-position: -28px -146px;width: 9px;height: 9px}#gc-toc ul .tlw-minus,#gc-toc ol .tlw-minus,.treelist .tlw-minus{background-image: url(/images/sprites08132008.png);background-position: -28px -210px;width: 9px;height: 9px}#gc-toc ul .tlw-plus: hover,#gc-toc ol .tlw-plus: hover,.treelist .tlw-plus: hover{background-image: url(/images/sprites08132008.png);background-position: -28px -344px;width: 9px;height: 9px}#gc-toc ul .tlw-minus: hover,#gc-toc ol .tlw-minus: hover,.treelist .tlw-minus: hover{background-image: url(/images/sprites08132008.png);background-position: -28px -408px;width: 9px;height: 9px}#gc-toc h1,#gc-toc h2{font-weight: bold;font-size: 100%;margin: 0;padding: 0;border: none;background: #fff}#gc-toc div.line{border-top: thin dotted #bbb;height: 1px;margin: 1.3em 1em 0 0;padding: 0}#gc-toc.hidden{display: none}#gc-toc.visible *{zoom: 1}#gc-pagecontent.expanded{margin-left: 0;width: auto}#gc-collapsible{position: absolute;left: -5px;top: 0;width: 3px;overflow: hidden;border: 2px solid #fff;border-top: none;border-bottom: none;background: #e5ecf9;cursor: pointer}.labs #gc-collapsible{background-color: #dcf6db}.depr #gc-collapsible{background-color: #ddd}#gc-collapsible.hover{border-color: #d3d9e5;border-width: 1px;width: 5px}.labs #gc-collapsible.hover{border-color: #090}.depr #gc-collapsible.hover{border-color: #333}#gc-collapsible-arrow{width: 4px;height: 12px;background: url(/images/hide-arrow.gif) top left no-repeat;position: absolute;margin-left: -15px;display: none;z-index: 5}#gc-collapsible-arrow.collapsed{background: url(/images/show-arrow.gif) top left no-repeat;margin-left: 17px}#navtoggle{position: absolute;top: 0;left: -8px}#toggleimgdiv{width: 10px;position: absolute;top: 0;cursor: pointer;display: block;z-index: 99}#toggleimgdiv img{margin: 9px auto;position: relative;visibility: hidden;top: 56%;height: 12px;width: 4px}#toggleimgdiv.hover img{visibility:}#toggleimgdiv.hover{border-right: 1px solid #e5ecf9;border-left: 1px solid #e5ecf9}#navtoggle a{position: absolute}#navhidearrow{display: block}#navshowarrow{display: none}#navtoggle.show #toggleimgdiv{}#navtoggle.show #navhidearrow{display: none}#navtoggle.show #navshowarrow{display: block}.g-tpl-190 #navtoggle{left: 185px}#gc-footer{clear: both;margin: 0;color: #666}#gc-footer .text{text-align: center;padding: 30px 0 0;margin: 0 0 0 0}#gc-footer .notice{padding: 0 0 8px 0}#search{margin: 12px 0 0 170px;width: 450px}#search table,#search table td{border: none;padding: 0;margin: 0;clear: none}#search form{margin: 0}.greytext{color: #aaa;font-size: small;height: 14px}#searchControl{display: none;margin-top: 3px;padding: 3px}#searchControl .gsc-search-box{display: none}#searchControl .gsc-control{width: 100%}#searchControl .gsc-ad-box{display: none}td.gsc-clear-button{display: none}.gsc-search-button{margin-left: 1px}.gsc-branding{display: none}.gsc-tabsArea{border-top: 1px solid #36C;background: #e5ecf9 none repeat scroll 0;padding: 5px 1px 4px;width: 100%}.gsc-tabsArea .gs-spacer{font-size: 0;margin-right: 0;overflow: hidden}.gsc-tabsArea .gs-spacer-opera{margin-right: 0}.gsc-tabsAreaInvisible{display: none}.gsc-tabHeader{display: inline;cursor: pointer;padding-left: 0;padding-right: 0;margin-right: 0;font-weight: bold}.gsc-tabHeader.gsc-tabhActive{position: relative;padding: 0 10px 0 5px;font-weight: bold;color: #000;cursor: auto}.gsc-tabHeader.gsc-tabhInactive{padding: 0 10px 0 5px;font-weight: normal;text-decoration: underline;color: #00C}.gsc-tabData.gsc-tabdActive{display: block}.gsc-tabData.gsc-tabdInactive{display: none}.gsc-resultsbox-visible{width: 42em}.gsc-results{clear: both;padding-bottom: 2px;padding-top: 15px}.gsc-results table,.gsc-results td{border: 0}#searchControl .gs-publisher{display: none}.gsc-result{margin-bottom: 1.2em}.gsc-result .gs-title{height: 1.4em;overflow: hidden;font-size: 110%}.gsc-result div.gs-watermark{display: none}.gsc-resultsHeader{display: none}.gsc-results .gsc-trailing-more-results{margin-bottom: 10px}.gsc-results .gsc-trailing-more-results,.gsc-results .gsc-trailing-more-results *{color: #00c;text-decoration: underline}.gsc-results .gsc-cursor-box .gsc-trailing-more-results{margin-bottom: 0;display: inline}.gsc-results .gsc-cursor{display: inline}.gsc-results .gsc-cursor-box{margin-bottom: 10px}.gsc-results .gsc-cursor-box .gsc-cursor-page{cursor: pointer;color: #000;text-decoration: underline;margin-right: 8px;display: inline}.gsc-results .gsc-cursor-box .gsc-cursor-current-page{color: #a90a08;font-weight: bold;text-decoration: none}.gs-result .gs-title,.gs-result .gs-title *{color: #00c;text-decoration: underline}.gs-divider{padding-bottom: 8px;text-align: center;color: #676767}.gs-result a.gs-visibleUrl,.gs-result .gs-visibleUrl{color: green;text-decoration: none}.gsc-webResult div.gs-visibleUrl-short{display: none}.gs-webResult div.gs-visibleUrl-long{width: 100%;overflow: hidden;display: block;white-space: nowrap}.str{color: #080}.kwd{color: #008}.com{color: #800}.typ{color: #606}.lit{color: #066}.pun{color: #660}.pln{color: #000}.tag{color: #008}.atn{color: #606}.atv{color: #080}.dec{color: #606}#gc-home{margin: .5em 1em 0}#gc-home .g-c-gc-home{padding: 0;overflow: hidden}#gc-home .g-first .g-c-gc-home{padding: 0 0 0 30px}.g-c-gc-home h2{padding-top: 1em;margin: 0}#gc-home h4{margin-top: 1em}#gc-home img{margin-top: 1em}#gc-codevideo{min-height: 120px}#gc-gadgets{margin: 2em 0 0;min-height: 230px}#gc-gadgets .g-unit .g-c{margin: 0 0 0 10px}#gc-gadgets .g-first .g-c{margin: 0 10px 0 0}.g-tpl-170 .g-unit,.g-unit .g-tpl-170 .g-unit,.g-unit .g-unit .g-tpl-170 .g-unit{margin: 0 0 0 170px;width: auto;float: none}.g-unit .g-unit .g-tpl-170 .g-first,.g-unit .g-tpl-170 .g-first,.g-tpl-170 .g-first{margin: 0;width: 170px;float: left}.g-tpl-170-alt .g-unit,.g-unit .g-tpl-170-alt .g-unit,.g-unit .g-unit .g-tpl-170-alt .g-unit{margin: 0 170px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-170-alt .g-first,.g-unit .g-tpl-170-alt .g-first,.g-tpl-170-alt .g-first{margin: 0;width: 170px;float: right}.g-tpl-190 .g-unit,.g-unit .g-tpl-190 .g-unit,.g-unit .g-unit .g-tpl-190 .g-unit{margin: 0 0 0 190px;width: auto;float: none}.g-unit .g-unit .g-tpl-190 .g-first,.g-unit .g-tpl-190 .g-first,.g-tpl-190 .g-first{margin: 0;width: 190px;float: left}.g-tpl-190-alt .g-unit,.g-unit .g-tpl-190-alt .g-unit,.g-unit .g-unit .g-tpl-190-alt .g-unit{margin: 0 190px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-190-alt .g-first,.g-unit .g-tpl-190-alt .g-first,.g-tpl-190-alt .g-first{margin: 0;width: 190px;float: right}.g-tpl-210 .g-unit,.g-unit .g-tpl-210 .g-unit,.g-unit .g-unit .g-tpl-210 .g-unit{margin: 0 0 0 210px;width: auto;float: none}.g-unit .g-unit .g-tpl-210 .g-first,.g-unit .g-tpl-210 .g-first,.g-tpl-210 .g-first{margin: 0;width: 210px;float: left}.g-tpl-210-alt .g-unit,.g-unit .g-tpl-210-alt .g-unit,.g-unit .g-unit .g-tpl-210-alt .g-unit{margin: 0 210px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-210-alt .g-first,.g-unit .g-tpl-210-alt .g-first,.g-tpl-210-alt .g-first{margin: 0;width: 210px;float: right}.g-tpl-230 .g-unit,.g-unit .g-tpl-230 .g-unit,.g-unit .g-unit .g-tpl-230 .g-unit{margin: 0 0 0 230px;width: auto;float: none}.g-unit .g-unit .g-tpl-230 .g-first,.g-unit .g-tpl-230 .g-first,.g-tpl-230 .g-first{margin: 0;width: 230px;float: left}.g-tpl-230-alt .g-unit,.g-unit .g-tpl-230-alt .g-unit,.g-unit .g-unit .g-tpl-230-alt .g-unit{margin: 0 230px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-230-alt .g-first,.g-unit .g-tpl-230-alt .g-first,.g-tpl-230-alt .g-first{margin: 0;width: 230px;float: right}.g-tpl-330 .g-unit,.g-unit .g-tpl-330 .g-unit,.g-unit .g-unit .g-tpl-330 .g-unit{margin: 0 0 0 330px;width: auto;float: none}.g-unit .g-unit .g-tpl-330 .g-first,.g-unit .g-tpl-330 .g-first,.g-tpl-330 .g-first{margin: 0;width: 330px;float: left}.g-tpl-330-alt .g-unit,.g-unit .g-tpl-330-alt .g-unit,.g-unit .g-unit .g-tpl-330-alt .g-unit{margin: 0 330px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-330-alt .g-first,.g-unit .g-tpl-330-alt .g-first,.g-tpl-330-alt .g-first{margin: 0;width: 330px;float: right}.g-tpl-370 .g-unit,.g-unit .g-tpl-370 .g-unit,.g-unit .g-unit .g-tpl-370 .g-unit{margin: 0 0 0 370px;width: auto;float: none}.g-unit .g-unit .g-tpl-370 .g-first,.g-unit .g-tpl-370 .g-first,.g-tpl-370 .g-first{margin: 0;width: 370px;float: left}.g-tpl-370-alt .g-unit,.g-unit .g-tpl-370-alt .g-unit,.g-unit .g-unit .g-tpl-370-alt .g-unit{margin: 0 370px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-370-alt .g-first,.g-unit .g-tpl-370-alt .g-first,.g-tpl-370-alt .g-first{margin: 0;width: 370px;float: right}.g-tpl-34-33-33-alt .g-unit,.g-unit .g-tpl-34-33-33-alt .g-unit,.g-unit .g-unit .g-tpl-34-33-33-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-34-33-33-alt .g-unit{width: 32.999%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-34-33-33-alt .g-first,.g-unit .g-unit .g-tpl-34-33-33-alt .g-first,.g-unit .g-tpl-34-33-33-alt .g-first,.g-tpl-34-33-33-alt .g-first{width: 33.999%;float: right;margin: 0}.g-tpl-34-33-33 .g-unit,.g-unit .g-tpl-34-33-33 .g-unit,.g-unit .g-unit .g-tpl-34-33-33 .g-unit,.g-unit .g-unit .g-unit .g-tpl-34-33-33 .g-unit{width: 32.999%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-34-33-33 .g-first,.g-unit .g-unit .g-tpl-34-33-33 .g-first,.g-unit .g-tpl-34-33-33 .g-first,.g-tpl-34-33-33 .g-first{width: 33.999%;float: left;margin: 0}}@media print{body{font-size: 9pt}#gb,#breadcrumbs,#gc-toc,#navtoggle{display: none}#search,#docs{visibility: hidden}#logo{margin: 0;padding: 0;position: static;display: block}.gbh{display: none;border: 0}#gc-topnav{display: block;clear: both}#gc-pagecontent{width: 6.75in;margin: 0;float: left;border: none;padding: 0 0 0 20px !important}pre{overflow: visible;text-wrap: unrestricted;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;white-space: pre-wrap;word-wrap: break-word}.backtotop,#date,#trail{visibility: hidden}h1,h2,h3,h4,h5,h6{page-break-after: avoid}table,img{page-break-inside: avoid}.hidden,.hidden *,#skipto,#skipto *{display: none}}.authornote{font-weight: bold;color: orangered;font-style: italic}.fcg-feature .feedflare{display: none}.exp-feat{font-size: smaller;font-family: arial, sans-serif;color: #000}.menuDiv{margin-top: 2px;border-color: #c9d7f1 #36C #36C #a2bae7;border-style: solid;border-width: 1px;z-index: 1001;padding: 0;width: 175px;background: #fff;overflow: hidden}.menuDiv .menuText{padding: 3px;text-decoration: none;background: #fff}#menuDiv-lang-dropdown{width: 115px !important}#menuIcon-lang-dropdown{margin-top: 2px}.menuDiv .menuItem{color: #00C;padding: 3px;text-decoration: none;background: #fff}.menuDiv .menuText{padding: 3px}.menuDiv .menuItem: hover{color: #fff !important;background: #36C;cursor: hand}.menuDiv .categoryTitle{padding-left: 1px}.menuDiv .menuCategory,.menuDiv .categoryTitle{margin-top: 4px}.menuDiv .menuSeparator{margin: 0 .5em;border: 0;border-top: 1px solid #c9d7f1}#gc-translate-direction{color: #00C}#lang-dropdown{background: transparent url('/images/globe2_small.png') no-repeat leftcenter;padding-left: 14px}#cs-searcharea a.unselectable{-moz-user-select: none;-user-select: none}#cs-recommended{text-align: left;background-color: #e3effe;font-style: italic}#cs-searchresults{position: absolute;margin-left: -.3em;background: #fff;line-height: 140%;border: 1px #c9c9c9 solid;cursor: pointer;position: absolute;z-index: 2010;display: none}#cs-searchresults a{text-decoration: none;color: #000}.cs-searchresult{padding: 0;display: block}img.collapsible-control{width: 9px;height: 9px;cursor: pointer;margin: 1px;vertical-align: middle}img.collapsible-control.collapsible-plus{background-image: url(/images/plus.gif)}img.collapsible-control.collapsible-plus.collapsible-hover{background-image: url(/images/plus_hover.gif)}img.collapsible-control.collapsible-minus{background-image: url(/images/minus.gif)}img.collapsible-control.collapsible-minus.collapsible-hover{background-image: url(/images/minus_hover.gif)}#googlecode-promo{background: #fff;border: 1px solid #ccc;font-size: 1em;margin: -46px 2px 2em 620px;padding: 5px 8px 8px 8px;text-align: center}#googlecode-promo sup{color: red;font-weight: normal}.confidential{background-color: #f6bc5d;font-weight: bold;font-size: 1.1em;padding: .3em;text-align: center}#commentWidgetTitle{background-color: #e5ecf9;border-top: 1px solid #36c}#commentwidget{margin: 1em 0 0}#commentwidget .zzCommentThreadGadget,#commentwidget .zzCommentList{background: none;margin: 0;padding: 0}#commentwidget .commentHeader{color: #666;border-top: 1px solid #c9d7f1;padding: 2px;margin: 3px 0}#commentwidget .zzNumUsersFoundThisHelpfulActive{color: #000;padding: 0 0 0 13px}#commentwidget .commentContent{margin: 5px 0;padding: 0;white-space: pre-wrap}#commentwidget .commentHelpful,#commentwidget .commentSpam{margin: 0 13px 1.3em 0;font-style: italic;font-size: 90%}#commentwidget .commentHelpful{float: left}#commentwidget .commentSpam{font-weight: bold}#commentwidget .zzVoteAffirmative,#commentwidget .zzVoteNegative,#commentwidget .zzReportSpamLink{color: #00c;font-style: normal;cursor: pointer}#commentwidget .zzVoteAffirmative: hover,#commentwidget .zzVoteNegative: hover,#commentwidget .zzReportSpamLink: hover{color: #000;font-style: italic}#commentwidget .zzNicknameGadgetNoLink,#commentwidget .zzTitleGadgetLink{color: #000}#commentwidget .zzNicknameGadgetNoLink,#commentwidget .zzNicknameGadgetLink{color: #000;cursor: normal;font-weight: bold;text-decoration: none}#commentwidget .zzTitleGadgetLink{}#commentwidget .zzCommentThreadSeparator,#commentwidget .zzCodeCommentThreadSeparator{border: none}#commentwidget .zzTextEditor{height: 6em;width: 90%;padding: 1px}--></style>
<title>Suishen Games API Reference/Testing</title>
<link rel="search" type="application/opensearchdescription+xml"
	title="Google Code" href="/osd.xml" />
<!--[if IE]><link rel="stylesheet" type="text/css" href="/css/iehacks.css" /><![endif]-->

<style type="text/css">
	::-webkit-scrollbar{width:10px;height:7px;}
	::-webkit-scrollbar-track{background-color:rgba(0,0,0,0.0);}
	::-webkit-scrollbar-track:hover{background-color:rgba(0,0,0,0.05);}
	::-webkit-scrollbar-thumb{background-color:rgba(0,175,219,0.4);}
	::-webkit-scrollbar-thumb:hover{background-color:rgba(0,175,219,0.7);}
</style>

<!-- google analytics begin -->
</head>
<body class="gc-documentation">
	<div id="gc-container">
		<div class="g-section g-tpl-170">
			<div class="g-unit g-first" id="gc-toc">
				<ul
					style="position: fixed; top: 0px; height: 100%; overflow: scroll;">
					<li>
						<h2>Navigation</h2>
						<ul>
							<li><a href="#15">首页</a></li>
							<li><a href="#65">美历日视图(V3)</a></li>
							<li><a href="#26">不喜欢首页推送内容(须登陆)</a></li>
							<li><a href="#33">用户反馈</a></li>
							&nbsp;
							<li><a href="#24">热门标签列表</a></li>
							<li><a href="#1">广场列表（热门，关注，主题 下的帖子）</a></li>
							<li><a href="#48">广场列表（最新 下的帖子）</a></li>
							<li><a href="#59">广场关注(V3)</a></li>
							<li><a href="#86">广场热门(V3)</a></li>
							<li><a href="#20">公众用户列表</a></li>
							<li><a href="#4">获取单个帖子详情</a></li>
							<li><a href="#3">具体帖子回复列表</a></li>
							<li><a href="#85">具体帖子投票列表(V3)</a></li>
							<li><a href="#11">回复我的帖子</a></li>
							<li><a href="#66">附近的帖子(V3)</a></li>
							&nbsp;
							<li><a href="#5">炫美发帖</a></li>
							<li><a href="#6">回帖</a></li>
							<li><a href="#39">美妞回复</a></li>
							<li><a href="#51">不喜欢帖子</a></li>
							<li><a href="#7">收藏一个帖子</a></li>
							<li><a href="#8">取消收藏一个帖子</a></li>
							
							<li><a href="#52">赞贴</a></li>
							<li><a href="#53">取消赞贴</a></li>
							
							<li><a href="#41">给帖子送鲜花</a></li>
							<li><a href="#42">给帖子鸡蛋</a></li>
							<li><a href="#43">取消给帖子送鲜花</a></li>
							<li><a href="#44">取消给帖子鸡蛋</a></li>
							&nbsp;
							<li><a href="#85">给帖子投票</a></li>
							&nbsp;
							<li><a href="#12">修改自己的帖子</a></li>
							<li><a href="#14">删除自己的帖子</a></li>
							<li><a href="#38">删除自己的评论</a></li>
							<li><a href="#25">举报非法的帖子</a></li>
							<li><a href="#34">举报非法的评论</a></li>
							<li><a href="#18">关注达人(必须登录)</a></li>
							<li><a href="#23">取消关注(必须登录)</a></li>
							<li><a href="#19">签到(必须登录)</a></li>
							<li><a href="#47">启动美历领取美分(需登录)</a></li>
							&nbsp;
							<li><a href="#13">检测帖子是否被收藏</a></li>
							<li><a href="#16">检测美妞、爱美、通知、美粉的更新</a></li>
							&nbsp;
							<li><a href="#54">注销</a></li>
							<li><a href="#17">用户信息</a></li>
							<li><a href="#74">获取我的信息(V3,需登录)</a></li>
							<li><a href="#79">修改我的信息(V3,需登录)</a></li>
							<li><a href="#75">查看用户的个人资料(V3)</a></li>
							<li><a href="#9">用户的发帖</a></li>
							<li><a href="#10">用户的收藏</a></li>	
							<li><a href="#21">用户关注的人</a></li>
							<li><a href="#22">关注该用户的人（粉丝）</a></li>
							<li><a href="#63">用户名模糊搜索</a></li>
							<li><a href="#69">用户信息批量获取(v3)</a></li>
							<li><a href="#70">姐妹列表(v3)</a></li>
							<li><a href="#88">TA的姐妹列表(v3)</a></li>
							<li><a href="#76">举报非法的用户</a></li>
							<li><a href="#90">我的黑名单列表</a></li>
							<li><a href="#77">加入黑名单</a></li>
							<li><a href="#78">取消加入黑名单</a></li>
							&nbsp;
							<li><a href="#71">创建聊天群组(v3)</a></li>
							<li><a href="#72">删除聊天群组(v3)</a></li>
							<li><a href="#80">更新聊天群组信息(v3)</a></li>
							<li><a href="#81">获取聊天群组信息(v3)</a></li>
							<li><a href="#73">群成员修改(v3)</a></li>
							<li><a href="#82">获取群成员(v3)</a></li>
							<li><a href="#83">群消息提醒开关设置(v3)</a></li>
							<li><a href="#84">获取群消息提醒开关(v3)</a></li>
							<li><a href="#89">获取用户加入的群组列表(v3)</a></li>
							<li><a href="#87">群成员主动退出(v3)</a></li>
							&nbsp;
							<li><a href="#29">注册、获取设备别名</a></li>
                            <li><a href="#27">消息列表</a></li>
                            <li><a href="#30">删除消息</a></li>
                            <li><a href="#31">更新消息为已读</a></li>
                            &nbsp;
                            <li><a href="#28">分享</a></li>
                            &nbsp;
                            <li><a href="#32">查询积分历史记录</a></li>
                            <li><a href="#46">查询当日获取积分情况</a></li>
                            &nbsp;
                            <li><a href="#36">查询用户生理周期数据</a></li>
                            <li><a href="#35">修改用户生理周期数据</a></li>
                            &nbsp;
                            <li><a href="#40">爱美顶部活动</a></li>
                            &nbsp;
                            <li><a href="#45">帖子查看、点击统计接口</a></li>
                            &nbsp;
                            <li><a href="#49">获取所有的状态</a></li>
                            <li><a href="#50">修改用户当前状态</a></li>
                            &nbsp;
                            <%-- ver2.1 added --%>
                            <li><a href="#55">经期、备孕、孕期详情</a></li>
                            &nbsp;
                            <li><a href="#56">美分商店</a></li>
                            <li><a href="#57">商品详情</a></li>
                            <li><a href="#58">兑换商品</a></li>
                            <li><a href="#60">获取收货地址</a></li>
                            <li><a href="#61">填写收货地址</a></li>
                            <li><a href="#62">设置商品分享状态</a></li>
                            
                            <br/>
						</ul>
					</li>
				</ul>
				<ul>
				</ul>
				<div class="line"></div>
			</div>
			
			<div class="g-unit" id="gc-pagecontent" style="margin-left: 250px;">
				<div id="gc-header">
					<div id="logo">
						<a href="javascript:void(0)"> <img
							src="http://www.suishen.mobi/wp-content/uploads/logo.png"
							alt="Etouch Code"
							style="border: 0; margin: 3px 0 0 0;" /> </a>
					</div>
					<div id="search">
						<div id="searchForm">
							<div id="gsc-search-box">
								<br/>
								随身云  API 测试文档
								<div id="cs-searchresults" onclick="event.cancelBubble = true;"></div>
								<div class="greytext">updated: <script language="javascript">document.write(new Date(document.lastModified)); </script></div>
							</div>
						</div>
						<!-- end searchForm -->
					</div>
					<!-- end search -->
					<br/><br/><br/>
				</div>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
                <h3 id="15">首页</h3>
                <form action="<%=ctx%>/api/smart" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/smart</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">local_svc_version</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="local_svc_version" type="text" value='1' /></td>
                            <td>客户端本地加载的服务版本号</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">ssy_installed</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="ssy_installed" type="text" value='' /></td>
                            <td>已安装的随身云应用的app_key；<br/>
                                                                                                若有多个，以逗号拼接，如：99817749,88762313</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">cycle</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="cycle" type="text" value='' /></td>
                            <td>用户当前生理周期<br/>
                             1:月经期第1、2天，2:月经期第3、4天，3:月经期第5、6天，4:月经期超过7天，5:排卵日前安全期，6:排卵期前,7:排卵日,8:排卵期后,9:排卵日后安全期</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">health</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="health" type="text" value='' /></td>
                            <td>
                            	用户当前健康症状（多个参数中间英文逗号分隔）<br/>
                            	1001：痘痘；2001：黑眼圈；3001：头晕；4001：乳房疼；5001：痛经；6001、6002、6003：血量少、血量正常、血量多；7001：便秘；7002：拉稀；8001：乏力；9001：呕吐 10001：胸闷；11001：水肿；12001：腰疼；13001：尿频
							</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">type</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="type" type="text" value='1' /></td>
                            <td>只获取健康相关数据</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">page</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="page" type="text" value='1' /></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回值中，一个item格式说明：<br/>
                            {<br/>
                                id:"",
								res: "",  -- res 取值：local表示本地模块，ota表示服务器推送信息<br/>
								svc_id: "", -- 当res=local时有效，标识具体那个本地模块；目前取值有horoscope,huangli;当res=ota时返回固定值dynamic<br/>
								view: { -- 当res=ota时view有效，内部存储要显示的内容和点击后目标地址<br/>
								    title: "", -- 标题<br/>
								    desc: "", -- 描述<br/>
								    images: [""], -- jsonArray格式,图片数组,<br/>
								    goto: "", -- 以meili://开头则进入响应本地模块并将参数传入, 如：meili://xuanmei_tag?id=姨妈；
								              以http://开头则进入webview,用webView加载该链接地址<br/>
								    from: "xuanmei" -- 内容的来源<br/>
						         }<br/>
						        -- 当goto是以 meili://xuanmei_single开头时，接口会返回帖子的收藏数和回复数<br/>
						        favoriteNum: 21,  --收藏数<br/>
                                replyNum: 18　--回复数<br/>
						     }
							</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                
                <h3 id="65">美历日视图（V3API）</h3>
                <form action="<%=ctx%>/api/v3/meili_daily" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/meili_daily</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="blue">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="blue">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>登录用户需要传递auth_token</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">period</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="period" type="text" value='0' /></td>
                            <td>用户当前身体所处的时期，0：经期，1：备孕，2：怀孕，3：育儿，非法值全部认为0.</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">cycle</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="cycle" type="text" value='1' /></td>
                            <td>用户当前月经周期，period != 2时有效<br/>
                             1:月经期第1、2天，2:月经期第3、4天，3:月经期第5、6天，4:月经期超过7天，5:排卵日前安全期，6:排卵期前,7:排卵日,8:排卵期后,9:排卵日后安全期</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">pregnancy_days</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="pregnancy_days" type="text" value='' /></td>
                            <td>用户当前怀孕天数，只在period = 2时有效。</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">body_index</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="body_index" type="text" value='2001' /></td>
                            <td>
                            	身体状态指数（多个参数中间英文逗号分隔）<br/>
                            	1001：痘痘；2001：黑眼圈；3001：头晕；4001：乳房疼；5001：痛经；6001、6002、6003：血量少、血量正常、血量多；7001：便秘；7002：拉稀；8001：乏力；9001：呕吐 10001：胸闷；11001：水肿；12001：腰疼；13001：尿频
							</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">focus_date</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="focus_date" type="text" value='20140911' /></td>
                            <td>日视图当前选中日期，必须为8位数字格式日期</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">last_resp_time</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="last_resp_time" type="text" value='0' /></td>
                            <td>上次响应数据中最大毫秒数</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">page</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="page" type="text" value='1' /></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回的type为帖子类型，包括：RTEXT, PHOTO, AUDIO, VIDEO, VOTE, URL, TASK</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				
				<h3 id="26">不喜欢首页推送内容</h3>
                <form action="<%=ctx%>/api/dislike" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/dislike</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">content_id</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="content_id" type="text" value='' /></td>
                            <td>不喜欢的首页推送内容的id</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="1">广场列表</h3>
				<form action="<%=ctx%>/api/square" method="get" >
					<table>
						<tr>
							<th width="10%">地址</th>
                            <th colspan="4"><%=ctx%>/api/square</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>opt</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">tag</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="tag" type="text" value='' /></td>
							<td>返回标签相关的帖子</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">is_follow</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="is_follow" type="text" value='' /></td>
							<td>返回我关注的人的帖子值填1</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td>
								<input style="width: 200px" name="page" type="text" value='' />
							</td>
							<td>page</td>
						</tr>
						
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h2 id="3">具体帖子回复列表</h2>
				<form action="<%=ctx%>/api/comments" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/comments</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>thread_id</td>
						</tr>
						
						<tr>
							<td>must</td>
							<td><font color="red">order_type</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="order_type" type="text" value='1' /></td>
							<td>排序方式，1->最新，2->最早</td>
						</tr>
						
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>只返回回复，不返回原帖信息</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h2 id="4">获取单个帖子详情</h2>
				<form action="<%=ctx%>/api/thread_details" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/thread_details</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						
						
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="5">炫美发帖</h3>
				<form action="<%=ctx%>/api/posting" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/posting</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">forum_id</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="forum_id" type="text" value='' /></td>
							<td>版块id,广场发帖传3,帮帮传1,</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">thread_type</font></td>
							<td>string</td>
							<td>
								<select name="thread_type" style="width: 180px">
									<option value="RTEXT">RTEXT</option>
									<option value="PHOTO">PHOTO</option>
									<option value="AUDIO">AUDIO</option>
									<option value="VIDEO">VIDEO</option>
								</select>
							</td>
							<td>帖子类型，RTEXT:纯文本，PHOTO:图片，AUDIO:语音，VIDEO:视频</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">title</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="title" type="text" value="" />
							</td>
							<td>帖子标题</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">content</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="content" type="text" value="" />
							</td>
							<td>帖子内容</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">cover</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="cover" type="text" value="" />
							</td>
							<td>帖子封面</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">cover_width</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="cover_width" type="text" value="" />
							</td>
							<td>封面宽度</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">cover_height</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="cover_height" type="text" value="" />
							</td>
							<td>封面高度</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">attachment_address</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="attachment_address" type="text" value="" />
							</td>
							<td>视频，声音，图片（广场里面的内容）的地址</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">tags</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="tags" type="text" value="" />
							</td>
							<td>帖子对应标签,多个标签之间用英文逗号分隔</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">lat</font></td>
							<td>double</td>
							<td><input style="width: 180px" name="lat" type="text" value="" />
							</td>
							<td>纬度（社区用户不可见）</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">lon</font></td>
							<td>double</td>
							<td><input style="width: 180px" name="lon" type="text" value="" />
							</td>
							<td>经度（社区用户不可见）</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">citykey</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="citykey" type="text" value="101010100" />
							</td>
							<td>根据经纬度坐标，定位到的地址，上传当前用户所在的城市ID，使用天气的citykey保持一致（社区用户不可见）</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">address</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="address" type="text" value="江苏省南京市雨花台区花神大道17号" />
							</td>
							<td>根据经纬度坐标，定位到的地址，上传当前用户所在的详细地址存储（社区用户不可见）</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="6">回帖</h3>
				<form action="<%=ctx%>/api/reply" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/reply</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td>
								<input style="width: 200px" name="uid" type="text" value='' />
							</td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">comment_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="comment_id" type="text" value='' /></td>
							<td>如果是子回复，则填写所回复的那条comment的id，如果是直接回复的主贴，则不填写。</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">reply_to_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="reply_to_uid" type="text" value='' /></td>
							<td>填写回复的帖子 或回复 的uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">content</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="content" type="text" value='' /></td>
							<td>回复内容</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">is_anonymous</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="is_anonymous" type="text" value='' /></td>
							<td>是否匿名， 0：不匿名 ，1：匿名</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				
				<!-- *************************************** ONE API BEGIN ************************************************ -->
                <h3 id="25">举报非法帖子(需登录)</h3>
                <form action="<%=ctx%>/api/report_illegal_thread" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/report_illegal_thread</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td>举报者uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">thread_id</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
                            <td>非法帖子id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">report_reason</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="report_reason" type="text" value='' /></td>
                            <td>举报理由 1:垃圾广告；2:色情淫秽；3:抄袭他人；4:虚假或诈骗信息；5:敏感信息；6:其他</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>--------</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="7">收藏一个帖子</h3>
				<form action="<%=ctx%>/api/favorite" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/favorite</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>被收藏的帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="8">取消收藏一个帖子</h3>
				<form action="<%=ctx%>/api/delete_favorite" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/delete_favorite</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>被收藏的帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="9">用户的发帖</h3>
				<form action="<%=ctx%>/api/user_threads" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_threads</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可以不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>目标用户uid,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>页数</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="10">用户的收藏</h3>
				<form action="<%=ctx%>/api/user_favorite" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_favorite</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可以不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>目标用户uid,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>页数</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="11">回复我的帖子</h3>
				<form action="<%=ctx%>/api/reply_me" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/reply_me</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td>
								<input style="width: 200px" name="auth_token" type="text" value='' />
							</td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td>
								<input style="width: 200px" name="uid" type="text" value='' />
							</td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td>
								<input style="width: 200px" name="page" type="text" value='' />
							</td>
							<td>页码</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="12">修改自己的帖子</h3>
				<form action="<%=ctx%>/api/edit_thread" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/edit_thread</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td>
								<input style="width: 200px" name="auth_token" type="text" value='' />
							</td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td>
								<input style="width: 200px" name="uid" type="text" value='' />
							</td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td>
								<input style="width: 200px" name="thread_id" type="text" value='' />
							</td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">thread_type</font></td>
							<td>string</td>
							<td>
								<select name="thread_type" style="width: 200px">
									<option value="RTEXT">RTEXT</option>
									<option value="PHOTO">PHOTO</option>
									<option value="AUDIO">AUDIO</option>
									<option value="VIDEO">VIDEO</option>
								</select>
							</td>
							<td>帖子类型,RTEXT:纯文本，PHOTO:图片，AUDIO:语音，VIDEO:视频</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">title</font></td>
							<td>string</td>
							<td>
								<input style="width: 200px" name="title" type="text" value='' />
							</td>
							<td>帖子标题</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">content</font></td>
							<td>string</td>
							<td>
								<input style="width: 200px" name="content" type="text" value='' />
							</td>
							<td>帖子内容</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">cover</font></td>
							<td>string</td>
							<td>
								<input style="width: 200px" name="cover" type="text" value='' />
							</td>
							<td>封面图片</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">cover_width</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="cover_width" type="text" value="" />
							</td>
							<td>封面宽度</td>
						</tr>
						<tr>
							<td>must</td> 
							<td><font color="red">cover_height</font></td>
							<td>int</td>
							<td><input style="width: 180px" name="cover_height" type="text" value="" />
							</td>
							<td>封面高度</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">attachment_address</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="attachment_address" type="text" value="" />
							</td>
							<td>视频，声音，图片（广场里面的内容）的地址</td>
						</tr>
						<tr>
							<td>opt</td> 
							<td><font color="red">tags</font></td>
							<td>string</td>
							<td><input style="width: 180px" name="tags" type="text" value="" />
							</td>
							<td>帖子对应标签,多个标签之间用英文逗号分隔</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="13">检测帖子是否被收藏</h3>
				<form action="<%=ctx%>/api/check_favorite" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/check_favorite</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="14">删除自己的帖子</h3>
				<form action="<%=ctx%>/api/delete_thread" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/delete_thread</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="16">检测美妞、爱美、通知、美粉的更新</h3>
				<form action="<%=ctx%>/api/check_updates" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/check_updates</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,非登录状态下不填写</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">timestamp</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="timestamp" type="text" value='' /></td>
							<td>客户端上次请求美妞、炫美版块或用户查看自己的粉丝列表的时间戳，json格式 {"meiniu":1756468489400,"xuanmei":17648646516800,"fans":13925641782},toString之后以UTF-8编码进行URLencode</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>登录用户返回值：{message: 2,fans: 1,xuanmei: 0,meiniu: 0}， 分别表示：未读通知数目；未查看粉丝数目；炫美是否有更新；美妞是否有更新。  未登录用户返回:{xuanmei: 1,meiniu: 0}</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="54">注销(必须登录)</h3>
                <form action="<%=ctx%>/api/logout" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/logout</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户uid</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				<h3 id="17">获取用户信息(头像、昵称、粉丝数、关注数、收藏数、是否已经关注)</h3>
				<form action="<%=ctx%>/api/userinfo" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userinfo</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">app_key</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="app_key" type="text" value='' /></td>
							<td>Android填66369085 ; iPhone填66369089</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid，可不填。不填写则视为未登录用户操作，关系均为未关注</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>查询目标的uid，必填、查自己就写自己的uid。</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>接口返回值中的user_status表示用户状态数据， 如果用户从未设置过他的状态，则接口返回值中无user_status；last_update_time最后一次更新用户状态时间。
							</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
                <h3 id="74">获取我的信息(V3)</h3>
                <form action="<%=ctx%>/api/v3/auth/myinfo" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/myinfo</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>Android填66369085 ; iPhone填66369089</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户uid</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回值中的user_status表示用户状态数据， 如果用户从未设置过他的状态，则接口返回值中无user_status；last_update_time最后一次更新用户状态时间。
                            </td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="79">修改我的信息(V3)</h3>
                <form action="<%=ctx%>/api/v3/auth/myinfo" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/myinfo</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>Android填66369085 ; iPhone填66369089</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户uid</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">nick</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="nick" type="text" value='' /></td>
                            <td>昵称，20字符以内，任意字母、数字、中文均可，不能含有特殊字符,如： [`~!@#$%^&*()+=|{}':;',\\[\\]./?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">avatar</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="avatar" type="text" value='' /></td>
                            <td>头像</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">city</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="city" type="text" value='' /></td>
                            <td>所在地区</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">birthday</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="birthday" type="text" value='' /></td>
                            <td>生日, 如:2014-05-03</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">birthday_type</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="birthday_type" type="text" value='' /></td>
                            <td>生日类型, 0农历, 1公历</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>修改用户信息接口; 1010 昵称已存在</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="75">查看用户的个人资料(V3)</h3>
                <form action="<%=ctx%>/api/v3/userinfo" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/userinfo</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>Android填66369085 ; iPhone填66369089</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>当前登录用户的uid,未登录不填</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">target_uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
                            <td>要查看资料的用户的uid</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>
                            </td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="18">关注达人(必须登录)</h3>
				<form action="<%=ctx%>/api/follow" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/follow</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>关注目标的uid</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="23">取消关注(必须登录)</h3>
				<form action="<%=ctx%>/api/follow_delete" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/follow_delete</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>取消关注目标的uid</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="19">签到</h3>
				<form action="<%=ctx%>/api/checkin" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/checkin</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td></td>
                        </tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户uid</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
                <h3 id="47">启动美历领取美分</h3>
                <form action="<%=ctx%>/api/check_login" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/check_login</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>此接口，登录用户启动美历时调用；</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="20">公众用户</h3>
				<form action="<%=ctx%>/api/public_users" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/public_users</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>is_follow取值：0未关注，1 已关注 ，2相互关注</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="21">用户关注的人</h3>
				<form action="<%=ctx%>/api/followers" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/followers</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>查询目标uid,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>is_follow取值：0未关注，1 已关注 ，2相互关注</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="22">关注该用户的人（粉丝）</h3>
				<form action="<%=ctx%>/api/fans" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/fans</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>查询目标uid,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>is_follow取值：0未关注，1 已关注 ，2相互关注</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="24">热门标签列表</h3>
				<form action="<%=ctx%>/api/tags" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/tags</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<h3 id="29">注册、获取设备别名</h3>
                <form action="<%=ctx%>/api/plisten" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/plisten</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>登录用户UID</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回值: {tags:["city_key","channel"],device_alias: "02e7e6b910441354226aa31f71f084ca"} tags:客户端需要设置的标签；device_alias:客户端别名帐号，登录用户返回。</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                
				<h3 id="27">消息列表</h3>
                <form action="<%=ctx%>/api/user_msg" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_msg</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">timestamp</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="timestamp" type="text" value='' /></td>
                            <td>当前页最后一个message的create_time,第一次请求可不传</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回的message按create_time降序排列； status属性表示消息的状态 1：创建  2：已读；msg_type表示消息类型：1:回复评论 2：喜欢</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                
                
				<h3 id="27">用户消息列表，针对ver2.1以上版本App</h3>
                <form action="<%=ctx%>/api/user_notes" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_notes</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font>timestamp</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="timestamp" type="text" value='' /></td>
                            <td>当前页最后一个message的create_time,第一次请求可不传</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font>msg_type</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="msg_type" type="text" value='' /></td>
                            <td>请求消息类型，1：别人评论我的；5：赞的消息；4：我评论别人的；9：系统消息，默认为1.</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font>page</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="page" type="text" value='' /></td>
                            <td>我的回复列表分页索引，仅当msg_type=4时使用</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>
                            	接口返回的message按create_time降序排列； status属性表示消息的状态 
                            	1：创建  2：已读；msg_type表示消息类型：1：别人给我的回复评论 5：别人赞了我的帖子 4：我回复别人的评论 9：系统消息<br/><br/>
                            	（如果请求系统消息，返回的SystemMessageType字段用于区分系统消息类别，1：admin删帖通知 2：admin删除评论通知 3：admin屏蔽账号/设备通知 4：admin解封账号/设备通知）
                            </td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                
                
                <h3 id="30">删除消息</h3>
                <form action="<%=ctx%>/api/delete_msg" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/delete_msg</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">post</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">del_type</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="del_type" type="text" value='' /></td>
                            <td>1:删除一条消息记录；2：清空消息列表</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">msg_id</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="msg_id" type="text" value='' /></td>
                            <td>del_type=1时，msg_id为待删除消息的id； del_type=2时，msg_id为最新一条消息的id；</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">msg_type</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="msg_type" type="text" value='' /></td>
                            <td>清空消息类型，1：别人评论我的；2：赞的消息；9：系统消息，只在清空消息时有用，del_type=2时必须.</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="31">更新消息为已读</h3>
                <form action="<%=ctx%>/api/update_msg" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/update_msg</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">post</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">msg_id</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="msg_id" type="text" value='' /></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                
                <h3 id="28">分享</h3>
                <form action="<%=ctx%>/api/share" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/share</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        
                        <tr>
                            <td>opt</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key, 未登录用户可不传</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID, 未登录用户可不传</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}, 未登录用户可不传</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">content_id</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="content_id" type="text" value='' /></td>
                            <td>分享内容的id，如： 首页内容id, 炫美帖子id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">content_type</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="content_type" type="text" value='' /></td>
                            <td>分享内容的类型，首页内容为 ：INDEX， 炫美帖子为：XUAN</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>未登录用户分享，不会增加美分</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <hr/>
                <h3 id="32">查询积分历史记录(需登录)</h3>
                <form action="<%=ctx%>/api/query_logs" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/query_logs</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="app_key" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="auth_token" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">timestamp</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="timestamp" type="text" value='' />
                            </td>
                            <td>当前页最后一条的create_time,第一次请求可不传</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回值： <br/>
                            {<br/>
                            rule_key: "ML_EVENT_AWARD",create_time: 1399278874055,<br/>
                            credits: 100,rule_name: "美历活动奖励"<br/>
                            }<br/>
rule_name何种操作触发美分+/-, 用于客户端在美分记录列表显示; rule_key兼容老版本;credits美分值，正值表示加美分，负值表示减美分；</td>
                        </tr>
                    </table>
                </form>
                
               <p>&nbsp;</p>
                <hr/>
                <h3 id="46"> 查询当日获取积分情况(需登录)</h3>
                <form action="<%=ctx%>/api/query_credits" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/query_credits</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="app_key" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="auth_token" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>接口返回值中：count代表该操作今天已经执行的次数，limit代表加分操作每天的限制次数  <br />
                            	json_key说明： <br />
                            	limit：今日完成次数  <br />
                            	award_limit: 今日总共次数<br />
                            	rule_key: 规则key <br />
                            	credits: 每次得分数 <br />
                            	rule_name: 规则名称<br />
                            </td>
                        </tr>
                    </table>
                </form> 
                
                
                <p>&nbsp;</p>
                <hr/>
                <h3 id="33">用户反馈(需登录)</h3>
                <form action="<%=ctx%>/api/feedback" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/feedback</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="app_key" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>String</td>
                            <td>
                                <input style="width: 200px" name="auth_token" type="text" value='' />
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">content</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="content" type="text" value='' />
                            </td>
                            <td>反馈内容</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <hr/>
                <h3 id="34">举报非法评论</h3>
            	<form action="<%=ctx%>/api/report_illegal_comments" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/report_illegal_comments</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td>举报者uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">comments_id</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="comments_id" type="text" value='' /></td>
                            <td>非法评论id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">report_reason</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="report_reason" type="text" value='' /></td>
                            <td>举报理由 1:垃圾广告；2:色情淫秽；3:抄袭他人；4:虚假或诈骗信息；5:敏感信息；6:其他</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>--------</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="36">查询用户生理周期数据</h3>
                <form action="<%=ctx%>/api/user_menstruation" method="get">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_menstruation</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">GET</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                        
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>无数据返回{}, 有数据接口返回{"average_days_of_menstruation":7,"average_menstrual_cycle":30,"last_menstrual_date":"2014-03-09","last_update_time":1397843014795} average_days_of_menstruation平均行经天数；average_menstrual_cycle平均月经周期；last_menstrual_date上次月经时间；last_update_time上次更新时间</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="35">修改用户生理周期数据</h3>
                <form action="<%=ctx%>/api/user_menstruation" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_menstruation</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">average_days_of_menstruation</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="average_days_of_menstruation" type="text" value='' /></td>
                            <td>平均行经天数,单位:天</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">average_menstrual_cycle</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="average_menstrual_cycle" type="text" value='' /></td>
                            <td>平均月经周期,单位:天</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">last_menstrual_date</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="last_menstrual_date" type="text" value='' /></td>
                            <td>上次月经时间,格式为: 2014-03-09</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>注意:后3个参数中至少有一个要传值</td>
                        </tr>
                    </table>
                </form>
                
                <p>&nbsp;</p>
                <h3 id="38">万众瞩目期待已久的删除评论接口闪亮登场</h3>
                <form action="<%=ctx%>/api/comments_delete" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/comments_delete</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>opt</td>
                            <td><font color="red">comments_id</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="comments_id" type="text" value='' /></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                
                <!-- 
                <p>&nbsp;</p>
                <h3 id="39">首页美妞回复</h3>
             	<form action="<%=ctx%>/api/meiniu_reply" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/meiniu_reply</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td>
								<input style="width: 200px" name="uid" type="text" value='' />
							</td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">comment_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="comment_id" type="text" value='' /></td>
							<td>如果是子回复，则填写所回复的那条comment的id，如果是直接回复的主贴，则不填写。</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">reply_to_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="reply_to_uid" type="text" value='' /></td>
							<td>填写回复的帖子 或回复 的uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">content</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="content" type="text" value='' /></td>
							<td>回复内容</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">is_anonymous</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="is_anonymous" type="text" value='' /></td>
							<td>是否匿名， 0：不匿名 ，1：匿名</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
				</form>
				-->
				<p>&nbsp;</p>
			
				<h3 id="40">爱美顶部活动</h3>
            	<form action="<%=ctx%>/api/campaign" method="get">
				<table>
					<tr>
						<th>地址</th>
                           <th colspan="4"><%=ctx%>/api/campaign</th>
					</tr>
					<tr>
						<th>方法</th>
                           <th colspan="4">GET</th>
					</tr>
					<tr>
						<th>必要性</th>
                           <th>参数名</th>
                           <th>类型</th>
						<th>测试</th>
						<th>说明</th>
					</tr>
					<tr>
                           <td>must</td>
                           <td><font color="red">app_key</font></td>
                           <td>int</td>
                           <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                           <td>app_key</td>
                       </tr>
					<tr>
						<td>opt</td>
						<td><font color="red">auth_token</font></td>
						<td>string</td>
						<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
						<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">uid</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="uid" type="text" value='' />
						</td>
						<td>uid</td>
					</tr>
					<tr>
						<td>--------</td>
						<td>--------</td>
						<td>--------</td>
						<td><input name="" type="submit" value="提交" /></td>
						<td>--------</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			
			<h3 id="41">给帖子鲜花</h3>
            	<form action="<%=ctx%>/api/flowers" method="post">
				<table>
					<tr>
						<th>地址</th>
                           <th colspan="4"><%=ctx%>/api/flowers</th>
					</tr>
					<tr>
						<th>方法</th>
                           <th colspan="4">POST</th>
					</tr>
					<tr>
						<th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
						<th>测试</th>
						<th>说明</th>
					</tr>
					<tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
                    <tr>
						<td>must</td>
						<td><font color="red">forum_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="forum_id" type="text" value='99186' />
						</td>
						<td>美妞 帖子 forum_id = 99186 </td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">vote</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="vote" type="text" value='' />
						</td>
						<td>vote  1鲜花 ，2鸡蛋，3默认</td>
					</tr>
					<tr>
						<td>must</td>
						<td><font color="red">thread_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="thread_id" type="text" value='' />
						</td>
						<td>thread_id</td>
					</tr>
					<tr>
						<td>--------</td>
						<td>--------</td>
						<td>--------</td>
						<td><input name="" type="submit" value="提交" /></td>
						<td>--------</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			
			<h3 id="42">给帖子鸡蛋</h3>
            	<form action="<%=ctx%>/api/eggs" method="post">
				<table>
					<tr>
						<th>地址</th>
                           <th colspan="4"><%=ctx%>/api/eggs</th>
					</tr>
					<tr>
						<th>方法</th>
                           <th colspan="4">POST</th>
					</tr>
					<tr>
						<th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
						<th>测试</th>
						<th>说明</th>
					</tr>
					<tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
                    <tr>
						<td>must</td>
						<td><font color="red">forum_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="forum_id" type="text" value='99186' />
						</td>
						<td>美妞 帖子 forum_id = 99186 </td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">vote</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="vote" type="text" value='' />
						</td>
						<td>vote  1鲜花 ，2鸡蛋，3默认</td>
					</tr>
					<tr>
						<td>must</td>
						<td><font color="red">thread_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="thread_id" type="text" value='' />
						</td>
						<td>thread_id</td>
					</tr>
					<tr>
						<td>--------</td>
						<td>--------</td>
						<td>--------</td>
						<td><input name="" type="submit" value="提交" /></td>
						<td>--------</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			
			<h3 id="43">取消给帖子鲜花</h3>
            	<form action="<%=ctx%>/api/delete_flowers" method="post">
				<table>
					<tr>
						<th>地址</th>
                           <th colspan="4"><%=ctx%>/api/delete_flowers</th>
					</tr>
					<tr>
						<th>方法</th>
                           <th colspan="4">POST</th>
					</tr>
					<tr>
						<th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
						<th>测试</th>
						<th>说明</th>
					</tr>
					<tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
                    <tr>
						<td>must</td>
						<td><font color="red">forum_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="forum_id" type="text" value='99186' />
						</td>
						<td>美妞 帖子 forum_id = 99186 </td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">vote</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="vote" type="text" value='' />
						</td>
						<td>vote  1鲜花 ，2鸡蛋，3默认</td>
					</tr>
					<tr>
						<td>must</td>
						<td><font color="red">thread_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="thread_id" type="text" value='' />
						</td>
						<td>thread_id</td>
					</tr>
					<tr>
						<td>--------</td>
						<td>--------</td>
						<td>--------</td>
						<td><input name="" type="submit" value="提交" /></td>
						<td>--------</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			
			<h3 id="44">取消给帖子鸡蛋</h3>
            	<form action="<%=ctx%>/api/delete_eggs" method="post">
				<table>
					<tr>
						<th>地址</th>
                           <th colspan="4"><%=ctx%>/api/delete_eggs</th>
					</tr>
					<tr>
						<th>方法</th>
                           <th colspan="4">POST</th>
					</tr>
					<tr>
						<th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
						<th>测试</th>
						<th>说明</th>
					</tr>
					<tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
                    <tr>
						<td>must</td>
						<td><font color="red">forum_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="forum_id" type="text" value='99186' />
						</td>
						<td>美妞 帖子 forum_id = 99186 </td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">vote</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="vote" type="text" value='' />
						</td>
						<td>vote  1鲜花 ，2鸡蛋，3默认</td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">thread_id</font></td>
						<td>long</td>
						<td>
							<input style="width: 200px" name="thread_id" type="text" value='' />
						</td>
						<td>thread_id</td>
					</tr>
					<tr>
						<td>--------</td>
						<td>--------</td>
						<td>--------</td>
						<td><input name="" type="submit" value="提交" /></td>
						<td>--------</td>
					</tr>
				</table>
			</form>
			<p>&nbsp;</p>
			
            <h3 id="45">客户端提交统计数据接口，处理客户端提交帖子浏览量、点击量等信息。</h3>
            <form action="<%=ctx%>/api/thread_summary" method="post">
            	<table>
                	<tr>
                        <th>地址</th>
                        <th colspan="4"><%=ctx%>/api/thread_summary</th>
                    </tr>
                    <tr>
                        <th>方法</th>
                        <th colspan="4">POST</th>
                    </tr>
                    <tr>
                        <th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
                        <th>测试</th>
                        <th>说明</th>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">thread_summary_json</font></td>
                        <td>string</td>
                        <td><input style="width: 200px;" name="thread_summary_json" type="text" value='' /></td>
                        <td>App提交的帖子浏览量等信息JSON字串，以base64进行编码 </td>
                    </tr>
                    <tr>
	                    <td>--------</td>
	                    <td>--------</td>
	                    <td>--------</td>
	                    <td><input name="" type="submit" value="提交" /></td>
	                    <td></td>
                	</tr>
                </table>
            </form>
            <p>&nbsp;</p>
            
            <h3 id="48">爱美 最新帖子 获取</h3>
            <form action="<%=ctx%>/api/square_latest" method="get">
            	<table>
                	<tr>
                        <th>地址</th>
                        <th colspan="4"><%=ctx%>/api/square_latest</th>
                    </tr>
                    <tr>
                        <th>方法</th>
                        <th colspan="4">GET</th>
                    </tr>
                    <tr>
                        <th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
                        <th>测试</th>
                        <th>说明</th>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
					<tr>
						<td>opt</td>
						<td><font color="red">auth_token</font></td>
						<td>string</td>
						<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
						<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
					</tr>
					<tr>
						<td>opt</td>
						<td><font color="red">uid</font></td>
						<td>long</td>
						<td><input style="width: 200px" name="uid" type="text" value='' /></td>
						<td>用户uid</td>
					</tr>
					<tr>
						<td>must</td>
						<td><font color="red">page</font></td>
						<td>int</td>
						<td>
							<input style="width: 200px" name="page" type="text" value='1' />
						</td>
						<td>page</td>
					</tr>
                    <tr>
	                    <td>--------</td>
	                    <td>--------</td>
	                    <td>--------</td>
	                    <td><input name="" type="submit" value="提交" /></td>
	                    <td></td>
                	</tr>
                </table>
            </form>
            <p>&nbsp;</p>
			<h3 id="49">获取所有的状态</h3>
            <form action="<%=ctx%>/api/user_status" method="get">
                <table>
                    <tr>
                        <th>地址</th>
                        <th colspan="4"><%=ctx%>/api/user_status</th>
                    </tr>
                    <tr>
                        <th>方法</th>
                        <th colspan="4">GET</th>
                    </tr>
                    <tr>
                        <th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
                        <th>测试</th>
                        <th>说明</th>
                    </tr>
                    <tr>
                        <td>--------</td>
                        <td>--------</td>
                        <td>--------</td>
                        <td><input name="" type="submit" value="提交" /></td>
                        <td>接口返回值：[{name: "月经",status: 1},{name: "怀孕",status: 2}]</td>
                    </tr>
                </table>
            </form>
            <p>&nbsp;</p>
            <h3 id="50">修改用户当前状态</h3>
            <form action="<%=ctx%>/api/user_status" method="post">
                <table>
                    <tr>
                        <th>地址</th>
                        <th colspan="4"><%=ctx%>/api/user_status</th>
                    </tr>
                    <tr>
                        <th>方法</th>
                        <th colspan="4">POST</th>
                    </tr>
                    <tr>
                        <th>必要性</th>
                        <th>参数名</th>
                        <th>类型</th>
                        <th>测试</th>
                        <th>说明</th>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">app_key</font></td>
                        <td>int</td>
                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                        <td>app_key</td>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">auth_token</font></td>
                        <td>string</td>
                        <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                        <td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">uid</font></td>
                        <td>long</td>
                        <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                        <td>用户uid</td>
                    </tr>
                    <tr>
                        <td>must</td>
                        <td><font color="red">status</font></td>
                        <td>int</td>
                        <td>
                            <input style="width: 200px" name="status" type="text" value='' />
                        </td>
                        <td>用户当前状态</td>
                    </tr>
                    <tr>
                        <td>--------</td>
                        <td>--------</td>
                        <td>--------</td>
                        <td><input name="" type="submit" value="提交" /></td>
                        <td></td>
                    </tr>
                </table>
            </form>
            <p>&nbsp;</p>
            <h3 id="51">不喜欢帖子</h3>
                <form action="<%=ctx%>/api/dislike_thread" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/dislike_thread</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>用户UID</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">thread_id</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
                            <td>不喜欢帖子id</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td></td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="52">赞贴</h3>
                <form action="<%=ctx%>/api/praise" method="post">
                   <table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/praise</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>被赞的帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
                </form>
                <p>&nbsp;</p>
                <h3 id="53">取消赞</h3>
                <form action="<%=ctx%>/api/delete_praise" method="post">
                    <table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/delete_praise</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>被赞的帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>--------</td>
						</tr>
					</table>
                </form>
                <p>&nbsp;</p>
                
                <h2 id="55">经期、备孕、孕期详情</h2>
				<form action="<%=ctx%>/api/menstruation_details" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/menstruation_details</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">type</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="type" type="text" value='' /></td>
							<td>请求数据类型，0：经期 1：备孕 2：孕期，与用户状态值一致</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">cycle</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="cycle" type="text" value='' /></td>
							<td>
								用户当前生理周期<br/>
								1:月经期第1、2天，2:月经期第3、4天，3:月经期第5、6天，4:月经期超过7天，5:排卵日前安全期，6:排卵期前,7:排卵日,8:排卵期后,9:排卵日后安全期
							</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">pregnancy_days</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="pregnancy_days" type="text" value='' /></td>
							<td>用户怀孕天数，只在type=2时有效。</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				
                <h2 id="56">美分商店-商品列表</h2>
				<form action="<%=ctx%>/api/credits_store" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/credits_store</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>opt</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}，用户未登录则不传</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid，用户未登录则不传</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>分页index，第1页可不传，从1开始</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				
                <h2 id="57">商品详情</h2>
				<form action="<%=ctx%>/api/goods_details" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/goods_details</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">goods_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="goods_id" type="text" value='' /></td>
							<td>商品id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				
				<h2 id="58">兑换商品</h2>
				<form action="<%=ctx%>/api/pay_goods" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/pay_goods</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">goods_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="goods_id" type="text" value='' /></td>
							<td>想要兑换的商品ID</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addr_has_changed</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="addr_has_changed" type="text" value='' /></td>
							<td>下单时，用户是否修改了收货地址，1:Yes,0:No</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">address_info</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="address_info" type="text" value='' /></td>
							<td>实物商品收件人信息，app拼装好该JSON字串</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="60">获取收货地址</h2>
				<form action="<%=ctx%>/api/user_address" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_address</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td><a href="<%=ctx%>/documents/get_address_info.json">返回JSON示例</a></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="61">填写收货地址</h2>
				<form action="<%=ctx%>/api/user_address" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_address</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">address_info</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="address_info" type="text" value='' /></td>
							<td>
								用户收货地址信息，JSON字串<br/><a href="<%=ctx%>/documents/set_address_info.json">提交JSON示例</a>
							</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h3 id="59">我的关注首页</h3>
                <form action="<%=ctx%>/api/v3/square_follow" method="get">
	            	<table>
	                	<tr>
	                        <th>地址</th>
	                        <th colspan="4"><%=ctx%>/api/v3/square_follow</th>
	                    </tr>
	                    <tr>
	                        <th>方法</th>
	                        <th colspan="4">GET</th>
	                    </tr>
	                    <tr>
	                        <th>必要性</th>
	                        <th>参数名</th>
	                        <th>类型</th>
	                        <th>测试</th>
	                        <th>说明</th>
	                    </tr>
	                    <tr>
	                        <td>must</td>
	                        <td><font color="red">app_key</font></td>
	                        <td>int</td>
	                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
	                        <td>app_key</td>
	                    </tr>
						<tr>
							<td>opt</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td>
								<input style="width: 200px" name="page" type="text" value='1' />
							</td>
							<td>page</td>
						</tr>
	                    <tr>
		                    <td>--------</td>
		                    <td>--------</td>
		                    <td>--------</td>
		                    <td><input name="" type="submit" value="提交" /></td>
		                    <td></td>
	                	</tr>
	                </table>
	            </form>
                <p>&nbsp;</p>
                
                <h3 id="86">我的热门首页</h3>
                <form action="<%=ctx%>/api/v3/square_hot" method="get">
	            	<table>
	                	<tr>
	                        <th>地址</th>
	                        <th colspan="4"><%=ctx%>/api/v3/square_hot</th>
	                    </tr>
	                    <tr>
	                        <th>方法</th>
	                        <th colspan="4">GET</th>
	                    </tr>
	                    <tr>
	                        <th>必要性</th>
	                        <th>参数名</th>
	                        <th>类型</th>
	                        <th>测试</th>
	                        <th>说明</th>
	                    </tr>
	                    <tr>
	                        <td>must</td>
	                        <td><font color="red">app_key</font></td>
	                        <td>int</td>
	                        <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
	                        <td>app_key</td>
	                    </tr>
						<tr>
							<td>opt</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td>
								<input style="width: 200px" name="page" type="text" value='1' />
							</td>
							<td>page</td>
						</tr>
	                    <tr>
		                    <td>--------</td>
		                    <td>--------</td>
		                    <td>--------</td>
		                    <td><input name="" type="submit" value="提交" /></td>
		                    <td></td>
	                	</tr>
	                </table>
	            </form>
                <p>&nbsp;</p>
				
				<h2 id="62">用户分享商品成功后，设置商品分享状态</h2>
				<form action="<%=ctx%>/api/share_goods" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/share_goods</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">goods_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="goods_id" type="text" value='' /></td>
							<td>想要分享的商品ID</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="63">根据用户名模糊搜索用户</h2>
				<form action="<%=ctx%>/api/user_search" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/user_search</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">nick_name</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="nick_name" type="text" value='' /></td>
							<td>用户名</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">page</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="page" type="text" value='1' /></td>
                            <td></td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<h2 id="69">根据环信ID批量获取用户信息</h2>
				<form action="<%=ctx%>/api/v3/auth/batchuserInfo" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/batchuserInfo</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">easemob_id</font></td>
                            <td>jsonArray</td>
                            <td><input style="width: 200px" name="easemob_id" type="text" value='' /></td>
                            <td>需要获取信息的环信id列表。格式jsonarray:["环信1","环信2","环信3"]</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<h3 id="70">姐妹列表</h3>
				<form action="<%=ctx%>/api/v3/auth/friends" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/friends</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">last_timestamp</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="last_timestamp" type="text" value='' /></td>
							<td>最后获取的时间戳</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				
				<h3 id="88">TA的姐妹列表</h3>
				<form action="<%=ctx%>/api/v3/TAfriends" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/TAfriends</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>option</td>
							<td><font color="blue">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>查询目标uid,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='' /></td>
							<td>page</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>is_follow取值：0未关注，1 已关注 ，2相互关注</td>
						</tr>
					</table>
				</form>
				
				<p>&nbsp;</p>
				<h2 id="71">创建聊天群组</h2>
				<form action="<%=ctx%>/api/v3/auth/createchatgroup" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/createchatgroup</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupname</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupname" type="text" value='' /></td>
                            <td>群组名称</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">desc</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="desc" type="text" value='' /></td>
                            <td>群组描述</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">members</font></td>
                            <td>jsonArray</td>
                            <td><input style="width: 200px" name="members" type="text" value='' /></td>
                            <td>需要获取信息的用户uid列表。格式jsonarray:["uid1","uid2","uid3"]</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<h2 id="72">删除聊天群组</h2>
				<form action="<%=ctx%>/api/v3/auth/delchatgroup" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/delchatgroup</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				<h2 id="73">群成员修改</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroupmember" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroupmember</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">member</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="member" type="text" value='' /></td>
                            <td>群成员["uid1","uid2"],添加删除都是这个结构</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">opt</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="opt" type="text" value='' /></td>
                            <td>操作类型——D：删人I：加人</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				<h2 id="80">更新聊天群组信息(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/updatechatgroup" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/updatechatgroup</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">groupname</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupname" type="text" value='' /></td>
                            <td>名称</td>
                        </tr>
                        <tr>
                            <td>optional</td>
                            <td><font color="blue">desc</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="desc" type="text" value='' /></td>
                            <td>群描述</td>
                        </tr>
                        <tr>
                            <td>optional</td>
                            <td><font color="blue">img_path</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="img_path" type="text" value='' /></td>
                            <td>群头像</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				<h2 id="81">获取聊天群组信息(v3)</h2>
				<form action="<%=ctx%>/api/v3/getchatgroup" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/getchatgroup</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<h2 id="82">获取群成员(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroupmember" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroupmember</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<h2 id="87">群成员主动退出(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroupquit" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroupquit</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<h2 id="83">群消息提醒开关设置(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroupremind" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroupremind</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">remind_flag</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="remind_flag" type="text" value='' /></td>
                            <td>1:需要提醒;0:不需要提醒</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<h2 id="84">获取群消息提醒开关(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroupremind" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroupremind</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">groupid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="groupid" type="text" value='' /></td>
                            <td>群组id,环信的id</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<h2 id="89">获取用户加入的群组列表(v3)</h2>
				<form action="<%=ctx%>/api/v3/auth/chatgroup_list" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/chatgroup_list</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>当前用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">target_uid</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
                            <td>目标用户的uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">last_timestamp</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="last_timestamp" type="text" value='' /></td>
                            <td>分页用。最后一次的获取的时间戳</td>
                        </tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>	
				
				<p>&nbsp;</p>
				
				<!-- *************************************** ONE API BEGIN ************************************************ -->
				<h3 id="66">附近的帖子</h3>
				<form action="<%=ctx%>/api/v3/threads/nearly" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/threads/nearly</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
							<th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
							<th>测试</th>
							<th>说明</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid,可以不填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">citykey</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="citykey" type="text" value='101190101' /></td>
							<td>城市id,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">lat</font></td>
							<td>double</td>
							<td><input style="width: 200px" name="lat" type="text" value='' /></td>
							<td>纬度,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">lon</font></td>
							<td>double</td>
							<td><input style="width: 200px" name="lon" type="text" value='' /></td>
							<td>经度,必填</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='1' /></td>
							<td>页数</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>警告：当返回帖子个数不足一页30个时，代表已没有更多帖子，请勿继续把page+1请求该接口。</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
                <h3 id="76">举报非法用户</h3>
            	<form action="<%=ctx%>/api/v3/auth/report_illegal_user" method="post">
                    <table>
                        <tr>
                            <th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/report_illegal_user</th>
                        </tr>
                        <tr>
                            <th>方法</th>
                            <th colspan="4">POST</th>
                        </tr>
                        <tr>
                            <th>必要性</th>
                            <th>参数名</th>
                            <th>类型</th>
                            <th>测试</th>
                            <th>说明</th>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>long</td>
                            <td>
                                <input style="width: 200px" name="uid" type="text" value='' />
                            </td>
                            <td>举报者uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">auth_token</font></td>
                            <td>string</td>
                            <td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
                            <td>base64编码 <br/>{"acctk":"","up":"","device":""}</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">illegal_uid</font></td>
                            <td>long</td>
                            <td><input style="width: 200px" name="illegal_uid" type="text" value='' /></td>
                            <td>非法用户uid</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">report_reason</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="report_reason" type="text" value='' /></td>
                            <td>举报理由 1:垃圾广告；2:色情淫秽；3:抄袭他人；4:虚假或诈骗信息；5:敏感信息；6:其他</td>
                        </tr>
                        <tr>
                            <td>--------</td>
                            <td>--------</td>
                            <td>--------</td>
                            <td><input name="" type="submit" value="提交" /></td>
                            <td>--------</td>
                        </tr>
                    </table>
                </form>
                <p>&nbsp;</p>
                <h3 id="77">加入黑名单(必须登录)</h3>
				<form action="<%=ctx%>/api/v3/auth/blacklist" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/blacklist</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>关注目标的uid</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<h3 id="78">取消加入黑名单(必须登录)</h3>
				<form action="<%=ctx%>/api/v3/auth/blacklist_delete" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/blacklist_delete</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">target_uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="target_uid" type="text" value='' /></td>
							<td>关注目标的uid</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<h3 id="85">获取单个帖子投票信息</h3>
				<form action="<%=ctx%>/api/v3/vote_description" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/vote_description</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<h3 id="85">给帖子投票</h3>
				<form action="<%=ctx%>/api/v3/user_vote" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/user_vote</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">POST</th>
						</tr>
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
                        <tr>
                            <td>must</td>
                            <td><font color="red">uid</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="uid" type="text" value='' /></td>
                            <td>uid</td>
                        </tr>
                        <tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">thread_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="thread_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">vote_id</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="vote_id" type="text" value='' /></td>
							<td>帖子id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<h3 id="90">我的黑名单列表(必须登录)</h3>
				<form action="<%=ctx%>/api/v3/auth/my_blacklist" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/v3/auth/my_blacklist</th>
						</tr>
						<tr>
							<th>方法</th>
                            <th colspan="4">GET</th>
						</tr>
						
						<tr>
                            <td>must</td>
                            <td><font color="red">app_key</font></td>
                            <td>int</td>
                            <td><input style="width: 200px" name="app_key" type="text" value='' /></td>
                            <td>app_key</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">auth_token</font></td>
							<td>string</td>
							<td><input style="width: 200px" name="auth_token" type="text" value='' /></td>
							<td>json的base64运算 <br/>{"acctk":"2f2DROID.ETOUCH","up":"ANDROID","device":"default"}</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>用户uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">page</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="page" type="text" value='1' /></td>
							<td>页数</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td></td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				<!-- *************************************** ONE API BEGIN ************************************************ -->
		</div>
		<!-- end gooey wrapper -->
	</div>
	<!-- end codesite content -->
	<div id="gc-footer" dir="ltr">
		<div class="text">
			<div class="notice">
				<div id="notice" style="text-align: center; border: 1em 0em 1em 0em">
				</div>
			</div>
			<span style="text-align: center; border: 1em 0em 1em 0em">Etouch
				Mobile Work </span><a href="http://www.etouch.cn">Etouch</a><br />
		</div>
	</div>
	<!-- end gc-footer -->
	<!-- end gc-container -->
	<div style="width:30px;height:30px;position:fixed;bottom:0px;right:0px;background-color:#BBB;padding:8px 8px 0px 8px;"><center><a href="#" style="text-decoration: none;font-weight: bold;">TOP</a></center></div>
</body>
</html>

