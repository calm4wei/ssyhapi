<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String ctx = request.getContextPath() ;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "DTD/xhtml1-transitional.dtd">
<html>
<head> 
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<style type="text/css"><!--/* Licensed under the Apache License, v. 2.0 (http: //www.apache.org/licenses/)*/.g-doc{width: 100%;text-align: left}.g-section{width: 100%;vertical-align: top;display: inline-block}*: first-child+html .g-section{display: block}* html .g-section{overflow: hidden}@-moz-document url-prefix (){.g-section{overflow: hidden}}@-moz-document url-prefix (){.g-section , tt: default{overflow : visible}}.g-doc-1024{width: 73.074em;*width: 71.313em;min-width: 950px;margin: 0 auto;text-align: left}.g-doc-800{width: 57.69em;*width: 56.3em;min-width: 750px;margin: 0 auto;text-align: left}.g-section: after{content: ".";display: block;height: 0;clear: both;visibility: hidden}.g-section,.g-unit{zoom: 1}.g-split .g-unit{text-align: right}.g-split .g-first{text-align: left}.g-tpl-160 .g-unit,.g-unit .g-tpl-160 .g-unit,.g-unit .g-unit .g-tpl-160 .g-unit{margin: 0 0 0 160px;width: auto;float: none}.g-unit .g-unit .g-tpl-160 .g-first,.g-unit .g-tpl-160 .g-first,.g-tpl-160 .g-first{margin: 0;width: 160px;float: left}.g-tpl-160-alt .g-unit,.g-unit .g-tpl-160-alt .g-unit,.g-unit .g-unit .g-tpl-160-alt .g-unit{margin: 0 160px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-160-alt .g-first,.g-unit .g-tpl-160-alt .g-first,.g-tpl-160-alt .g-first{margin: 0;width: 160px;float: right}.g-tpl-180 .g-unit,.g-unit .g-tpl-180 .g-unit,.g-unit .g-unit .g-tpl-180 .g-unit{margin: 0 0 0 180px;width: auto;float: none}.g-unit .g-unit .g-tpl-180 .g-first,.g-unit .g-tpl-180 .g-first,.g-tpl-180 .g-first{margin: 0;width: 180px;float: left}.g-tpl-180-alt .g-unit,.g-unit .g-tpl-180-alt .g-unit,.g-unit .g-unit .g-tpl-180-alt .g-unit{margin: 0 180px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-180-alt .g-first,.g-unit .g-tpl-180-alt .g-first,.g-tpl-180-alt .g-first{margin: 0;width: 180px;float: right}.g-tpl-200 .g-unit,.g-unit .g-tpl-200 .g-unit,.g-unit .g-unit .g-tpl-200 .g-unit{margin: 0 0 0 200px;width: auto;float: none}.g-unit .g-unit .g-tpl-200 .g-first,.g-unit .g-tpl-200 .g-first,.g-tpl-200 .g-first{margin: 0;width: 200px;float: left}.g-tpl-225 .g-unit,.g-unit .g-tpl-225 .g-unit,.g-unit .g-unit .g-tpl-225 .g-unit{margin: 0 0 0 226px;width: auto;float: none}.g-unit .g-unit .g-tpl-225 .g-first,.g-unit .g-tpl-225 .g-first,.g-tpl-225 .g-first{margin: 0;width: 226px;float: left}.g-tpl-250 .g-unit,.g-unit .g-tpl-250 .g-unit,.g-unit .g-unit .g-tpl-250 .g-unit{margin: 0 0 0 250px;width: auto;float: none}.g-unit .g-unit .g-tpl-250 .g-first,.g-unit .g-tpl-250 .g-first,.g-tpl-250 .g-first{margin: 0;width: 250px;float: left}.g-tpl-250-alt .g-unit,.g-unit .g-tpl-250-alt .g-unit,.g-unit .g-unit .g-tpl-250-alt .g-unit{margin: 0 250px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-250-alt .g-first,.g-unit .g-tpl-250-alt .g-first,.g-tpl-250-alt .g-first{margin: 0;width: 250px;float: right}.g-tpl-300 .g-unit,.g-unit .g-tpl-300 .g-unit,.g-unit .g-unit .g-tpl-300 .g-unit{margin: 0 0 0 300px;width: auto;float: none}.g-unit .g-unit .g-tpl-300 .g-first,.g-unit .g-tpl-300 .g-first,.g-tpl-300 .g-first{margin: 0;width: 300px;float: left}.g-tpl-300-alt .g-unit,.g-unit .g-tpl-300-alt .g-unit,.g-unit .g-unit .g-tpl-300-alt .g-unit{margin: 0 300px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-300-alt .g-first,.g-unit .g-tpl-300-alt .g-first,.g-tpl-300-alt .g-first{margin: 0;width: 300px;float: right}.g-tpl-25-75 .g-unit,.g-unit .g-tpl-25-75 .g-unit,.g-unit .g-unit .g-tpl-25-75 .g-unit,.g-unit .g-unit .g-unit .g-tpl-25-75 .g-unit{width: 75%;float: right;margin: 0}.g-tpl-25-75-alt .g-unit,.g-unit .g-tpl-25-75-alt .g-unit,.g-unit .g-unit .g-tpl-25-75-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-25-75-alt .g-unit{width: 25%;float: left;margin: 0}.g-tpl-75-25-alt .g-unit,.g-unit .g-tpl-75-25-alt .g-unit,.g-unit .g-unit .g-tpl-75-25-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-75-25-alt .g-unit{width: 75%;float: left;margin: 0}.g-tpl-75-25 .g-unit,.g-unit .g-tpl-75-25 .g-unit,.g-unit .g-unit .g-tpl-75-25 .g-unit,.g-unit .g-unit .g-unit .g-tpl-75-25 .g-unit{width: 25%;float: right;margin: 0}.g-tpl-33-67 .g-unit,.g-unit .g-tpl-33-67 .g-unit,.g-unit .g-unit .g-tpl-33-67 .g-unit,.g-unit .g-unit .g-unit .g-tpl-33-67 .g-unit{width: 67%;float: right;margin: 0}.g-tpl-33-67-alt .g-unit,.g-unit .g-tpl-33-67-alt .g-unit,.g-unit .g-unit .g-tpl-33-67-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-33-67-alt .g-unit{width: 33%;float: left;margin: 0}.g-tpl-67-33-alt .g-unit,.g-unit .g-tpl-67-33-alt .g-unit,.g-unit .g-unit .g-tpl-67-33-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-67-33-alt .g-unit{width: 67%;float: left;margin: 0}.g-tpl-67-33 .g-unit,.g-unit .g-tpl-67-33 .g-unit,.g-unit .g-unit .g-tpl-67-33 .g-unit,.g-unit .g-unit .g-unit .g-tpl-67-33 .g-unit{width: 33%;float: right;margin: 0}.g-tpl-50-50 .g-unit,.g-unit .g-tpl-50-50 .g-unit,.g-unit .g-unit .g-tpl-50-50 .g-unit,.g-unit .g-unit .g-unit .g-tpl-50-50 .g-unit{width: 50%;float: right;margin: 0}.g-tpl-50-50-alt .g-unit,.g-unit .g-tpl-50-50-alt .g-unit,.g-unit .g-unit .g-tpl-50-50-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-50-50-alt .g-unit{width: 50%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-tpl-75-25 .g-first,.g-tpl-25-75 .g-first{width: 25%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-25-75-alt .g-first,.g-unit .g-unit .g-tpl-25-75-alt .g-first,.g-unit .g-tpl-25-75-alt .g-first,.g-tpl-25-75-alt .g-first{width: 75%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25-alt .g-first,.g-unit .g-unit .g-tpl-75-25-alt .g-first,.g-unit .g-tpl-75-25-alt .g-first,.g-tpl-75-25-alt .g-first{width: 25%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-unit .g-tpl-75-25 .g-first,.g-unit .g-tpl-75-25 .g-first,.g-tpl-75-25 .g-first{width: 75%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-33-67 .g-first,.g-unit .g-unit .g-tpl-33-67 .g-first,.g-unit .g-tpl-33-67 .g-first,.g-tpl-33-67 .g-first{width: 33%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-33-67-alt .g-first,.g-unit .g-unit .g-tpl-33-67-alt .g-first,.g-unit .g-tpl-33-67-alt .g-first,.g-tpl-33-67-alt .g-first{width: 67%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-67-33-alt .g-first,.g-unit .g-unit .g-tpl-67-33-alt .g-first,.g-unit .g-tpl-67-33-alt .g-first,.g-tpl-67-33-alt .g-first{width: 33%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-67-33 .g-first,.g-unit .g-unit .g-tpl-67-33 .g-first,.g-unit .g-tpl-67-33 .g-first,.g-tpl-67-33 .g-first{width: 67%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-50-50 .g-first,.g-unit .g-unit .g-tpl-50-50 .g-first,.g-unit .g-tpl-50-50 .g-first,.g-tpl-50-50 .g-first{width: 50%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-50-50-alt .g-first,.g-unit .g-unit .g-tpl-50-50-alt .g-first,.g-unit .g-tpl-50-50-alt .g-first,.g-tpl-50-50-alt .g-first{width: 50%;float: right;margin: 0}.g-tpl-nest{width: auto}.g-tpl-nest .g-section{display: inline}.g-tpl-nest .g-unit,.g-unit .g-tpl-nest .g-unit,.g-unit .g-unit .g-tpl-nest .g-unit,.g-unit .g-unit .g-unit .g-tpl-nest .g-unit{float: left;width: auto;margin: 0}.g-tpl-nest-alt .g-unit,.g-unit .g-tpl-nest-alt .g-unit,.g-unit .g-unit .g-tpl-nest-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-nest-alt .g-unit{float: right;width: auto;margin: 0}@media screen , projection , print{html,body{height: auto;margin: 0;padding: 0}body{font-family: Helvetica, Arial, sans-serif;font-size: small;color: #000;background-color: #fff;margin: 3px 8px}a,a: link{color: #00c}a: active{color: red}a: visited{color: #551a8b}p{padding: 1em 0 0 0;margin: 0;line-height: 125%}img{border: none;padding: 0;margin: 0}a img{border: 1px solid;padding: 1px}code,pre{font-family: monospace;color: #007000}code{font-size: 10pt}pre{font-size: 9pt;background-color: #fafafa;border: 1px solid #bbb;line-height: 125%;margin: 1em 0 0 0;padding: .99em;overflow: auto;word-wrap: break-word}form{margin: 1em 0 0 0;padding: 0}blockquote{text-align: justify;margin: 1em 90px 0 70px;padding: 0 20px}hr{border: 1px solid;border-color: #bbb;margin: 1em 0}h1,h2,h3,h4{margin-bottom: 0}h1{font-size: 160%}h2{font-size: 140%}h3{font-size: 120%}h4{font-size: 110%}#gc-pagecontent h1{font-size: 130%;font-weight: bold;margin: 2em 0 0 -10px;padding: 1px 3px;position: relative;border-top: 1px solid #36C;background-color: #e5ecf9}.labs #gc-pagecontent h1{background-color: #dcf6db;border-top: 1px solid #090}.depr #gc-pagecontent h1{background-color: #ddd;border-top: 1px solid #333}#gc-pagecontent h2{font-size: 130%;font-weight: bold;margin: 1.5em 0 0 0}#gc-pagecontent h3{font-size: 110%;margin: .7em 0 0 0;position: relative;top: .4em}#gc-pagecontent h4{font-size: 100%;font-weight: bold;margin: .6em 0 0 0;position: relative;top: .4em;z-index: 5}#gc-pagecontent h5{font-size: 100%;font-weight: normal;font-style: italic;text-decoration: underline;margin: .4em 0 0 0;position: relative;top: .4em;z-index: 5}#gc-pagecontent h1.page_title,#gc-pagecontent h2.page_title{line-height: 130%;font-size: 170%;margin: 0 0 0 -10px;padding: .8em 0 0;border: none;background: none}ol,ul{padding: 0;margin: .5em 0 0 15px;line-height: 125%}li ol,li ul{padding: 0;margin: 0 0 0 10px}li{margin: .3em 0 0 1.5em;padding: 0}dl{margin: 0;padding: 0;line-height: 125%}dt{font-weight: bold;margin: .75em 0 0 0;padding: 0}dd{margin: .4em 0 0 2em;padding: 0;font-weight: normal}li pre{margin: .5em 0 .6em 0}li p,dd p{padding: .5em 0 .6em 0;margin: 0}ol.alpha{list-style: lower-alpha}ol.alphacap{list-style: upper-alpha}ol.roman{list-style: lower-roman}ol.romancap{list-style: upper-roman}ol.termslist{list-style-type: decimal}ol.termslist>li>ol{list-style-type: lower-alpha}ol.termslist>li>ol>li>ol{list-style-type: lower-roman}.tablelist{margin: 0 0 1em 0}.listhead li{font-weight: bold}.listhead li *,.listhead li li{font-weight: normal}.code li{font-family: monospace;font-size: 10pt}.code li p,.code li li{font-family: Arial, Helvetica, sans-serif}li p.note,li p.warning,li p.labswarning,li p.caution{margin: .8em 0 0 0;padding: .2em .5em .2em .9em}ol.toc,div.toc ol{margin: 1em 0 0 0;padding: 0;list-style: none}ol.toc li,div.toc ol li{font-weight: bold;margin: .5em 0 0 1.5em;padding: 0}ol.toc li ol,div.toc ol li ol{margin: 0;padding: 0}ol.toc li ol li,div.toc ol li ol li{padding: 0;margin: .1em 0 0 1em;font-weight: normal;list-style: none}table ol.toc{margin-left: 0}table{border-collapse: collapse;line-height: 125%}th,td{text-align: left;vertical-align: top}table{margin: 1em 0 0 1px;border: 1px solid;border-color: #bbb;border-spacing: 0;border-collapse: collapse;clear: right}th{font-weight: bold;text-align: left;padding: 6px 12px;border: 1px solid #bbb;background-color: #e5ecf9}.labs #gc-pagecontent th{background-color: #dcf6db}td{padding: 6px 12px;border: 1px solid #bbb;background-color: #fff;text-align: left;vertical-align: top}td h1,td h2,td h3,td h4,td h5{margin-left: 0}td ul,td ol{margin: 0 0 1em 15px;padding: 0}td p,td dl{margin: 0 0 1em 0;padding: 0}td *+p{margin: 1em 0 0 0;padding: 0}tr.alt td,td.alt{background: #f5f5f5}table.columns{border: none;margin: 1em 0 0}table.columns td{border: none;padding: 0 3em 0 0}table.columns td p{margin: 1em 0 0 0;padding: 0}.ftdpartners{border: none;border-top: 1px solid #c9d7f1;margin-top: 2em}.ftdpartners td{padding: 0;text-align: center;border: none;vertical-align: middle}.ftdpartners .ftdpartners-col{width: 30%;text-align: left}.deprecated,.deprecated *,.deprecated a: link,.deprecated a: hover,.deprecated a: visited{color: #9b9b9b}.grey{color: #666}.blue{color: #36e}.iconlist{margin-left: 0}.iconlist li{padding: 0 0 0 50px;margin: 0 0 30px 0;list-style: none}.topmargin{margin-top: 1em !important}.nomargin{margin: 0 !important}.nopadding{padding: 0 !important}.noborder{border: none !important}.border{border: 1px solid gray;padding: 1px}.normalsize{font-size: 100% !important}.nolist li{margin-left: 0;list-style: none}.noindent{margin-left: 0}.doublespace,.doublespace li{margin-top: 1em}ul.doublespace,ol.doublespace{margin-top: 0;padding-top: 0}.terms,.termsbox{margin: 1em 0 0 0;padding: .5em;background-color: #efefef}.terms form,.termsbox form{margin: 0;padding: 0}.terms table,.termsbox table,.terms td,.termsbox td{border: none;background-color: transparent}div.row{float: left;clear: left;position: relative;margin: 1em 0 0;padding: 0}.left{float: left;margin: 0 1em 0 0;padding: 0}div.special{border: 1px solid;padding: 0 .9em .9em;margin: 1em 0 0 0;background-color: #e5ecf9;border-color: #36C}.labs div.special{background-color: #dcf6db;border: 1px solid #090}p.note,p.caution,p.warning{margin: 1em 0 0 0;padding: .2em .5em .2em .9em;background-color: #efefef;border-top: 1px solid;border-bottom: 1px solid}p.labswarning{margin: 1em 0 0 0;padding: .2em .5em .2em 20px;background: url(/images/labs-11.png) top left no-repeat;background-position: 4px 6px;background-color: #dcf6db;border-top: 1px solid;border-bottom: 1px solid;border-color: #090}p.note{border-color: #bbb}p.caution{border-color: #fc3}p.warning{border-color: #a03}p.warning b,p.warning em,p.warning strong{color: #a03;font-weight: bold}p.caption{padding: 1px 0 0;margin: 0}div.linkbox{margin: 1em 25px 1em 0;padding: .5em 0}div.linkbox a{font-size: 100%;font-weight: bold;border: 1px solid;margin: 0;padding: .5em;background-color: #e5ecf9;border-color: #36C}.promo{margin: 1em 1em 0 0;padding: 0 1em 1em;border: 1px solid #36C}.new{font-size: 78%;font-weight: bold;color: red;text-decoration: none}.subscribe{float: right}.subscribe a,.subscribe a: visited{text-decoration: none;color: #bb4900}.subscribe a .linktext{text-decoration: underline}.subscribe img{width: 15px;height: 15px;vertical-align: middle;padding-bottom: 2px}p#date,p.date{text-align: left;margin: 0;padding: 1em;font-style: italic;font-size: x-small}p.backtotop{float: right;margin: .5em 0 0 4em;position: relative;padding: 0;font-size: x-small;z-index: 99}div.sidebox{width: 22em;margin: 1em 0 0 20px;padding: 0 0 1em 1em;border-left: 1px dotted silver;float: right;clear: right;position: relative;background-color: #fff;position: relative;z-index: 100}div.sidebox h2{font-size: 110%;font-weight: bold;margin: 0 5px 0 -5px;padding: .1em 3px .1em 5px;background-color: #e5ecf9;border-color: #36C}.hidden,.hidden *,#skipto,#skipto *,#skipto a,#skipto a: hover,#skipto a: visited{position: relative;top: -9999px;left: -9999px;height: 0;width: 0;overflow: hidden;z-index: 4444}#skipto,#skipto *,#skipto a,#skipto a: hover,#skipto a: visited{_position: relative}#skipto a: focus{position: fixed;top: 5px;left: 5px;height: auto;width: auto;background-color: #fff;border: 2px solid #00C;font-weight: bold;padding: 5px}#gc-container{margin: 0;padding: 0;max-width: 1160px}#gc-pagecontent,#gc-home{position: relative}#gc-pagecontent{padding-left: 24px;/*border-left: 3px solid #e5ecf9*/}#deprecatewarn{background: #ff8;margin-bottom: -20px;text-align: center;padding: 3px 1px 3px 1px}#gb{width: 100%;padding: 3px 0 0 0;text-align: right}#gb a{text-decoration: none;color: #00c}img.globeicon{border: medium none;vertical-align: bottom;padding: 0;margin-right: 1px;margin-top: 2px}.gbh{border-top: 1px solid #c9d7f1;font-size: 1px;height: 0;position: absolute;top: 24px;width: 100%}#langwarn{text-align: right;font-size: 80%;margin-bottom: -13px;color: #666}.moreright{margin: 2px 0 2px 0;text-align: right}#gc-header{padding: 7px 0 0 0;margin: 0px ;}#gc-header #logo{margin: 0;padding-top: 5px;position: absolute}#gc-header a img{border: none;padding: 0;margin: 0}#gc-header #gc-logo-img{background-image: url(/images/sprites08132008.png);background-position: -28px -36px;width: 153px;height: 55px;cursor: pointer}#codesiteContent{margin-top: 3px;padding: 3px}#gc-topnav{font-size: 1em;margin: 0;padding: .1em 0;width: 100%;white-space: nowrap;word-wrap: normal;background-color: #e5ecf9;border-top: 1px solid #36C}.labs #gc-topnav{background-color: #dcf6db;border-top: 1px solid #090}.depr #gc-topnav{background-color: #ddd;border-top: 1px solid #333}#gc-topnav h1{font-size: 1.5em;line-height: 1.3em;font-weight: bold;background-color: transparent;border: 0;margin: 0;padding: 0 0 0 26px;float: left}#gc-topnav h1.msie{padding-left: 0}#gc-topnav h1.mozilla{padding-left: 1px}#gc-topnav h1.default{padding-left: 2px}#gc-topnav img{padding: .3em .3em .3em .5em;float: left;cursor: pointer}#gc-topnav ul{line-height: 1em;text-align: right;list-style: none;margin: 0;padding: .47em 0}#gc-topnav ul li{float: none;display: inline;margin: 0;padding: 0}#gc-topnav li a{padding: 0 .8em}.cs-breadcrumbs{list-style: none;margin: 0 0 0 -20px;padding: .5em 0 0 10px;position: relative}.cs-breadcrumbs li{display: inline;padding: 0 0 0 0;margin: 0}a.selected,.selected a,a.selected: visited,.selected a: visited{color: #000;text-decoration: none}#gc-topnav a.selected,#gc-topnav a.selected: visited{font-weight: bold}#gc-toc{clear: left;padding: .5em 0 0 0 !important;margin-bottom: 1em;height: auto}#gc-toc *{zoom: 1}#gc-toc ul,#gc-toc ol{padding: .6em 0 0;margin: 0;line-height: 120%}#gc-toc ul ul,#gc-toc ol ol{padding: 0;margin: 0}#gc-toc ul *,#gc-toc ol *,.treelist *{vertical-align: middle}#gc-toc li a{padding-right: 4px;_padding-right: 0}#gc-toc ul li,#gc-toc ol li,#gc-toc .treelist li{list-style: none;padding: .2em 0 .2em 2px;margin: 0}#gc-toc ul li li,#gc-toc ol li li,#gc-toc .treelist li li{padding: .25em 0 .25em 15px}.treelist li h2{display: inline}#gc-toc ul h1,#gc-toc ol h1,#gc-toc ul h2,#gc-toc ol h2{font-weight: bold;font-size: 100%;margin: 0;padding: .2em 0;border: none;background: #fff}#gc-toc ul .selected,#gc-toc ol .selected,.treelist .selected{background-color: #e5ecf9;color: #000;text-decoration: none;z-index: 2;position: relative}.labs #gc-toc ul .selected,.labs #gc-toc ol .selected,.labs .treelist .selected{background-color: #dcf6db}.depr #gc-toc ul .selected,.depr #gc-toc ol .selected,.depr .treelist .selected{background-color: #ddd}#gc-toc ul div.tlw-title,#gc-toc ol div.tlw-title,#gc-toc .treelist div.tlw-title{position: relative;margin: 0 0 0 -15px;padding: .2em 0 .2em 15px}.treelist .tlw-hidden{display: none}.treelist div.tlw-title{position: relative;margin-left: -15px;padding-left: 15px}.treelist div.tlw-title a.tlw-control{padding: 0;margin: 0 0 0 -12px;overflow: hidden;background-color: transparent}#gc-toc ul div.tlw-title img.tlw-control,#gc-toc ol div.tlw-title img.tlw-control,.treelist div.tlw-title img.tlw-control{position: relative;height: inherit;width: 9px;margin: 0 0 3px !important;padding: 0;border: none}#gc-toc ul .tlw-plus,#gc-toc ol .tlw-plus,.treelist .tlw-plus{background-image: url(/images/sprites08132008.png);background-position: -28px -146px;width: 9px;height: 9px}#gc-toc ul .tlw-minus,#gc-toc ol .tlw-minus,.treelist .tlw-minus{background-image: url(/images/sprites08132008.png);background-position: -28px -210px;width: 9px;height: 9px}#gc-toc ul .tlw-plus: hover,#gc-toc ol .tlw-plus: hover,.treelist .tlw-plus: hover{background-image: url(/images/sprites08132008.png);background-position: -28px -344px;width: 9px;height: 9px}#gc-toc ul .tlw-minus: hover,#gc-toc ol .tlw-minus: hover,.treelist .tlw-minus: hover{background-image: url(/images/sprites08132008.png);background-position: -28px -408px;width: 9px;height: 9px}#gc-toc h1,#gc-toc h2{font-weight: bold;font-size: 100%;margin: 0;padding: 0;border: none;background: #fff}#gc-toc div.line{border-top: thin dotted #bbb;height: 1px;margin: 1.3em 1em 0 0;padding: 0}#gc-toc.hidden{display: none}#gc-toc.visible *{zoom: 1}#gc-pagecontent.expanded{margin-left: 0;width: auto}#gc-collapsible{position: absolute;left: -5px;top: 0;width: 3px;overflow: hidden;border: 2px solid #fff;border-top: none;border-bottom: none;background: #e5ecf9;cursor: pointer}.labs #gc-collapsible{background-color: #dcf6db}.depr #gc-collapsible{background-color: #ddd}#gc-collapsible.hover{border-color: #d3d9e5;border-width: 1px;width: 5px}.labs #gc-collapsible.hover{border-color: #090}.depr #gc-collapsible.hover{border-color: #333}#gc-collapsible-arrow{width: 4px;height: 12px;background: url(/images/hide-arrow.gif) top left no-repeat;position: absolute;margin-left: -15px;display: none;z-index: 5}#gc-collapsible-arrow.collapsed{background: url(/images/show-arrow.gif) top left no-repeat;margin-left: 17px}#navtoggle{position: absolute;top: 0;left: -8px}#toggleimgdiv{width: 10px;position: absolute;top: 0;cursor: pointer;display: block;z-index: 99}#toggleimgdiv img{margin: 9px auto;position: relative;visibility: hidden;top: 56%;height: 12px;width: 4px}#toggleimgdiv.hover img{visibility:}#toggleimgdiv.hover{border-right: 1px solid #e5ecf9;border-left: 1px solid #e5ecf9}#navtoggle a{position: absolute}#navhidearrow{display: block}#navshowarrow{display: none}#navtoggle.show #toggleimgdiv{}#navtoggle.show #navhidearrow{display: none}#navtoggle.show #navshowarrow{display: block}.g-tpl-190 #navtoggle{left: 185px}#gc-footer{clear: both;margin: 0;color: #666}#gc-footer .text{text-align: center;padding: 30px 0 0;margin: 0 0 0 0}#gc-footer .notice{padding: 0 0 8px 0}#search{margin: 12px 0 0 170px;width: 450px}#search table,#search table td{border: none;padding: 0;margin: 0;clear: none}#search form{margin: 0}.greytext{color: #aaa;font-size: small;height: 14px}#searchControl{display: none;margin-top: 3px;padding: 3px}#searchControl .gsc-search-box{display: none}#searchControl .gsc-control{width: 100%}#searchControl .gsc-ad-box{display: none}td.gsc-clear-button{display: none}.gsc-search-button{margin-left: 1px}.gsc-branding{display: none}.gsc-tabsArea{border-top: 1px solid #36C;background: #e5ecf9 none repeat scroll 0;padding: 5px 1px 4px;width: 100%}.gsc-tabsArea .gs-spacer{font-size: 0;margin-right: 0;overflow: hidden}.gsc-tabsArea .gs-spacer-opera{margin-right: 0}.gsc-tabsAreaInvisible{display: none}.gsc-tabHeader{display: inline;cursor: pointer;padding-left: 0;padding-right: 0;margin-right: 0;font-weight: bold}.gsc-tabHeader.gsc-tabhActive{position: relative;padding: 0 10px 0 5px;font-weight: bold;color: #000;cursor: auto}.gsc-tabHeader.gsc-tabhInactive{padding: 0 10px 0 5px;font-weight: normal;text-decoration: underline;color: #00C}.gsc-tabData.gsc-tabdActive{display: block}.gsc-tabData.gsc-tabdInactive{display: none}.gsc-resultsbox-visible{width: 42em}.gsc-results{clear: both;padding-bottom: 2px;padding-top: 15px}.gsc-results table,.gsc-results td{border: 0}#searchControl .gs-publisher{display: none}.gsc-result{margin-bottom: 1.2em}.gsc-result .gs-title{height: 1.4em;overflow: hidden;font-size: 110%}.gsc-result div.gs-watermark{display: none}.gsc-resultsHeader{display: none}.gsc-results .gsc-trailing-more-results{margin-bottom: 10px}.gsc-results .gsc-trailing-more-results,.gsc-results .gsc-trailing-more-results *{color: #00c;text-decoration: underline}.gsc-results .gsc-cursor-box .gsc-trailing-more-results{margin-bottom: 0;display: inline}.gsc-results .gsc-cursor{display: inline}.gsc-results .gsc-cursor-box{margin-bottom: 10px}.gsc-results .gsc-cursor-box .gsc-cursor-page{cursor: pointer;color: #000;text-decoration: underline;margin-right: 8px;display: inline}.gsc-results .gsc-cursor-box .gsc-cursor-current-page{color: #a90a08;font-weight: bold;text-decoration: none}.gs-result .gs-title,.gs-result .gs-title *{color: #00c;text-decoration: underline}.gs-divider{padding-bottom: 8px;text-align: center;color: #676767}.gs-result a.gs-visibleUrl,.gs-result .gs-visibleUrl{color: green;text-decoration: none}.gsc-webResult div.gs-visibleUrl-short{display: none}.gs-webResult div.gs-visibleUrl-long{width: 100%;overflow: hidden;display: block;white-space: nowrap}.str{color: #080}.kwd{color: #008}.com{color: #800}.typ{color: #606}.lit{color: #066}.pun{color: #660}.pln{color: #000}.tag{color: #008}.atn{color: #606}.atv{color: #080}.dec{color: #606}#gc-home{margin: .5em 1em 0}#gc-home .g-c-gc-home{padding: 0;overflow: hidden}#gc-home .g-first .g-c-gc-home{padding: 0 0 0 30px}.g-c-gc-home h2{padding-top: 1em;margin: 0}#gc-home h4{margin-top: 1em}#gc-home img{margin-top: 1em}#gc-codevideo{min-height: 120px}#gc-gadgets{margin: 2em 0 0;min-height: 230px}#gc-gadgets .g-unit .g-c{margin: 0 0 0 10px}#gc-gadgets .g-first .g-c{margin: 0 10px 0 0}.g-tpl-170 .g-unit,.g-unit .g-tpl-170 .g-unit,.g-unit .g-unit .g-tpl-170 .g-unit{margin: 0 0 0 170px;width: auto;float: none}.g-unit .g-unit .g-tpl-170 .g-first,.g-unit .g-tpl-170 .g-first,.g-tpl-170 .g-first{margin: 0;width: 170px;float: left}.g-tpl-170-alt .g-unit,.g-unit .g-tpl-170-alt .g-unit,.g-unit .g-unit .g-tpl-170-alt .g-unit{margin: 0 170px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-170-alt .g-first,.g-unit .g-tpl-170-alt .g-first,.g-tpl-170-alt .g-first{margin: 0;width: 170px;float: right}.g-tpl-190 .g-unit,.g-unit .g-tpl-190 .g-unit,.g-unit .g-unit .g-tpl-190 .g-unit{margin: 0 0 0 190px;width: auto;float: none}.g-unit .g-unit .g-tpl-190 .g-first,.g-unit .g-tpl-190 .g-first,.g-tpl-190 .g-first{margin: 0;width: 190px;float: left}.g-tpl-190-alt .g-unit,.g-unit .g-tpl-190-alt .g-unit,.g-unit .g-unit .g-tpl-190-alt .g-unit{margin: 0 190px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-190-alt .g-first,.g-unit .g-tpl-190-alt .g-first,.g-tpl-190-alt .g-first{margin: 0;width: 190px;float: right}.g-tpl-210 .g-unit,.g-unit .g-tpl-210 .g-unit,.g-unit .g-unit .g-tpl-210 .g-unit{margin: 0 0 0 210px;width: auto;float: none}.g-unit .g-unit .g-tpl-210 .g-first,.g-unit .g-tpl-210 .g-first,.g-tpl-210 .g-first{margin: 0;width: 210px;float: left}.g-tpl-210-alt .g-unit,.g-unit .g-tpl-210-alt .g-unit,.g-unit .g-unit .g-tpl-210-alt .g-unit{margin: 0 210px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-210-alt .g-first,.g-unit .g-tpl-210-alt .g-first,.g-tpl-210-alt .g-first{margin: 0;width: 210px;float: right}.g-tpl-230 .g-unit,.g-unit .g-tpl-230 .g-unit,.g-unit .g-unit .g-tpl-230 .g-unit{margin: 0 0 0 230px;width: auto;float: none}.g-unit .g-unit .g-tpl-230 .g-first,.g-unit .g-tpl-230 .g-first,.g-tpl-230 .g-first{margin: 0;width: 230px;float: left}.g-tpl-230-alt .g-unit,.g-unit .g-tpl-230-alt .g-unit,.g-unit .g-unit .g-tpl-230-alt .g-unit{margin: 0 230px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-230-alt .g-first,.g-unit .g-tpl-230-alt .g-first,.g-tpl-230-alt .g-first{margin: 0;width: 230px;float: right}.g-tpl-330 .g-unit,.g-unit .g-tpl-330 .g-unit,.g-unit .g-unit .g-tpl-330 .g-unit{margin: 0 0 0 330px;width: auto;float: none}.g-unit .g-unit .g-tpl-330 .g-first,.g-unit .g-tpl-330 .g-first,.g-tpl-330 .g-first{margin: 0;width: 330px;float: left}.g-tpl-330-alt .g-unit,.g-unit .g-tpl-330-alt .g-unit,.g-unit .g-unit .g-tpl-330-alt .g-unit{margin: 0 330px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-330-alt .g-first,.g-unit .g-tpl-330-alt .g-first,.g-tpl-330-alt .g-first{margin: 0;width: 330px;float: right}.g-tpl-370 .g-unit,.g-unit .g-tpl-370 .g-unit,.g-unit .g-unit .g-tpl-370 .g-unit{margin: 0 0 0 370px;width: auto;float: none}.g-unit .g-unit .g-tpl-370 .g-first,.g-unit .g-tpl-370 .g-first,.g-tpl-370 .g-first{margin: 0;width: 370px;float: left}.g-tpl-370-alt .g-unit,.g-unit .g-tpl-370-alt .g-unit,.g-unit .g-unit .g-tpl-370-alt .g-unit{margin: 0 370px 0 0;width: auto;float: none}.g-unit .g-unit .g-tpl-370-alt .g-first,.g-unit .g-tpl-370-alt .g-first,.g-tpl-370-alt .g-first{margin: 0;width: 370px;float: right}.g-tpl-34-33-33-alt .g-unit,.g-unit .g-tpl-34-33-33-alt .g-unit,.g-unit .g-unit .g-tpl-34-33-33-alt .g-unit,.g-unit .g-unit .g-unit .g-tpl-34-33-33-alt .g-unit{width: 32.999%;float: right;margin: 0}.g-unit .g-unit .g-unit .g-tpl-34-33-33-alt .g-first,.g-unit .g-unit .g-tpl-34-33-33-alt .g-first,.g-unit .g-tpl-34-33-33-alt .g-first,.g-tpl-34-33-33-alt .g-first{width: 33.999%;float: right;margin: 0}.g-tpl-34-33-33 .g-unit,.g-unit .g-tpl-34-33-33 .g-unit,.g-unit .g-unit .g-tpl-34-33-33 .g-unit,.g-unit .g-unit .g-unit .g-tpl-34-33-33 .g-unit{width: 32.999%;float: left;margin: 0}.g-unit .g-unit .g-unit .g-tpl-34-33-33 .g-first,.g-unit .g-unit .g-tpl-34-33-33 .g-first,.g-unit .g-tpl-34-33-33 .g-first,.g-tpl-34-33-33 .g-first{width: 33.999%;float: left;margin: 0}}@media print{body{font-size: 9pt}#gb,#breadcrumbs,#gc-toc,#navtoggle{display: none}#search,#docs{visibility: hidden}#logo{margin: 0;padding: 0;position: static;display: block}.gbh{display: none;border: 0}#gc-topnav{display: block;clear: both}#gc-pagecontent{width: 6.75in;margin: 0;float: left;border: none;padding: 0 0 0 20px !important}pre{overflow: visible;text-wrap: unrestricted;white-space: -moz-pre-wrap;white-space: -pre-wrap;white-space: -o-pre-wrap;white-space: pre-wrap;word-wrap: break-word}.backtotop,#date,#trail{visibility: hidden}h1,h2,h3,h4,h5,h6{page-break-after: avoid}table,img{page-break-inside: avoid}.hidden,.hidden *,#skipto,#skipto *{display: none}}.authornote{font-weight: bold;color: orangered;font-style: italic}.fcg-feature .feedflare{display: none}.exp-feat{font-size: smaller;font-family: arial, sans-serif;color: #000}.menuDiv{margin-top: 2px;border-color: #c9d7f1 #36C #36C #a2bae7;border-style: solid;border-width: 1px;z-index: 1001;padding: 0;width: 175px;background: #fff;overflow: hidden}.menuDiv .menuText{padding: 3px;text-decoration: none;background: #fff}#menuDiv-lang-dropdown{width: 115px !important}#menuIcon-lang-dropdown{margin-top: 2px}.menuDiv .menuItem{color: #00C;padding: 3px;text-decoration: none;background: #fff}.menuDiv .menuText{padding: 3px}.menuDiv .menuItem: hover{color: #fff !important;background: #36C;cursor: hand}.menuDiv .categoryTitle{padding-left: 1px}.menuDiv .menuCategory,.menuDiv .categoryTitle{margin-top: 4px}.menuDiv .menuSeparator{margin: 0 .5em;border: 0;border-top: 1px solid #c9d7f1}#gc-translate-direction{color: #00C}#lang-dropdown{background: transparent url('/images/globe2_small.png') no-repeat leftcenter;padding-left: 14px}#cs-searcharea a.unselectable{-moz-user-select: none;-user-select: none}#cs-recommended{text-align: left;background-color: #e3effe;font-style: italic}#cs-searchresults{position: absolute;margin-left: -.3em;background: #fff;line-height: 140%;border: 1px #c9c9c9 solid;cursor: pointer;position: absolute;z-index: 2010;display: none}#cs-searchresults a{text-decoration: none;color: #000}.cs-searchresult{padding: 0;display: block}img.collapsible-control{width: 9px;height: 9px;cursor: pointer;margin: 1px;vertical-align: middle}img.collapsible-control.collapsible-plus{background-image: url(/images/plus.gif)}img.collapsible-control.collapsible-plus.collapsible-hover{background-image: url(/images/plus_hover.gif)}img.collapsible-control.collapsible-minus{background-image: url(/images/minus.gif)}img.collapsible-control.collapsible-minus.collapsible-hover{background-image: url(/images/minus_hover.gif)}#googlecode-promo{background: #fff;border: 1px solid #ccc;font-size: 1em;margin: -46px 2px 2em 620px;padding: 5px 8px 8px 8px;text-align: center}#googlecode-promo sup{color: red;font-weight: normal}.confidential{background-color: #f6bc5d;font-weight: bold;font-size: 1.1em;padding: .3em;text-align: center}#commentWidgetTitle{background-color: #e5ecf9;border-top: 1px solid #36c}#commentwidget{margin: 1em 0 0}#commentwidget .zzCommentThreadGadget,#commentwidget .zzCommentList{background: none;margin: 0;padding: 0}#commentwidget .commentHeader{color: #666;border-top: 1px solid #c9d7f1;padding: 2px;margin: 3px 0}#commentwidget .zzNumUsersFoundThisHelpfulActive{color: #000;padding: 0 0 0 13px}#commentwidget .commentContent{margin: 5px 0;padding: 0;white-space: pre-wrap}#commentwidget .commentHelpful,#commentwidget .commentSpam{margin: 0 13px 1.3em 0;font-style: italic;font-size: 90%}#commentwidget .commentHelpful{float: left}#commentwidget .commentSpam{font-weight: bold}#commentwidget .zzVoteAffirmative,#commentwidget .zzVoteNegative,#commentwidget .zzReportSpamLink{color: #00c;font-style: normal;cursor: pointer}#commentwidget .zzVoteAffirmative: hover,#commentwidget .zzVoteNegative: hover,#commentwidget .zzReportSpamLink: hover{color: #000;font-style: italic}#commentwidget .zzNicknameGadgetNoLink,#commentwidget .zzTitleGadgetLink{color: #000}#commentwidget .zzNicknameGadgetNoLink,#commentwidget .zzNicknameGadgetLink{color: #000;cursor: normal;font-weight: bold;text-decoration: none}#commentwidget .zzTitleGadgetLink{}#commentwidget .zzCommentThreadSeparator,#commentwidget .zzCodeCommentThreadSeparator{border: none}#commentwidget .zzTextEditor{height: 6em;width: 90%;padding: 1px}--></style>
<title>随手优惠  API 测试文档</title>
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
							<li><a href="#userAddress_add">新增收货地址</a></li>
							<li><a href="#userAddress_delete">删除收货地址</a></li>
							<li><a href="#userAddress_update">修改收货地址</a></li>
							<li><a href="#userAddress_updateDefaultAddr">用户设置/取消某条收货地址为默认</a></li>
                            <li><a href="#userAddress_getAll">获取用户全部收货地址</a></li>
                            <li><a href="#userAddress_getById">获取用户某条收货地址详情</a></li>
                            
                            <li style="margin-top: 20px;"><a href="#returnOrder">用户退款/退货</a></li>
                            
                            <li style="margin-top: 20px;"><a href="#mauStats">用户月度活跃时长统计</a></li>
                            
                            <li style="margin-top: 20px;"><a href="#feedback_get">查询意见反馈</a></li>
                            <li><a href="#feedback_post">发表意见反馈</a></li>
                            
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
						<a href="javascript:void(0)">
						<img
							src="http://youhui.cn/Epai/home/images/logo.png"
							alt="随手优惠，点我省钱！"
							style="border: 0; margin: 3px 0 0 50;" /></a>
					</div>
					<div id="search">
						<div id="searchForm">
							<div id="gsc-search-box">
								<br/>
								随手优惠  API 测试文档
								<div id="cs-searchresults" onclick="event.cancelBubble = true;"></div>
								<div class="greytext">updated: <script language="javascript">document.write(new Date(document.lastModified)); </script></div>
							</div>
						</div>
						<!-- end searchForm -->
					</div>
					<!-- end search -->
					<br/><br/><br/>
				</div>
				<!-- *************************************** API TEST FORM BEGIN ************************************************ -->
                
				<h2 id="userAddress_add">新增收货地址</h2>
				<form action="<%=ctx%>/api/userAddress?type=add" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=add</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">name</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="name" type="text" value='' /></td>
							<td>收货人姓名</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">phoneNumber</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="phoneNumber" type="text" value='' /></td>
							<td>收货人电话</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">addressProvince</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressProvince" type="text" value='' /></td>
							<td>省份</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressCity</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressCity" type="text" value='' /></td>
							<td>城市</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressDetail</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressDetail" type="text" value='' /></td>
							<td>具体地址</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">postcode</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="postcode" type="text" value='' /></td>
							<td>邮编</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">isDefault</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="isDefault" type="text" value='' /></td>
							<td>是否为用户默认收货地址，0：不是，1：是，不传默认为0</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回新增地址信息：</span><br/>
								{"status":{"code":1000,"desc":"OK","showDesc":"成功"},
								"item":{"uid":"66013309","id":45,"addressCity":"滁州市","phoneNumber":"15997887687","isDefault":1,"name":"贝主席","postcode":"210002","addressProvince":"安徽省","addressDetail":"某地"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="userAddress_delete">删除收货地址</h2>
				<form action="<%=ctx%>/api/userAddress?type=delete" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=delete</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressId</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="addressId" type="text" value='' /></td>
							<td>收货地址ID</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="userAddress_update">修改收货地址</h2>
				<form action="<%=ctx%>/api/userAddress?type=update" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=post</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressId</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="addressId" type="text" value='' /></td>
							<td>收货地址ID</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">name</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="name" type="text" value='' /></td>
							<td>收货人姓名</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">phoneNumber</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="phoneNumber" type="text" value='' /></td>
							<td>收货人电话</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">addressProvince</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressProvince" type="text" value='' /></td>
							<td>省份</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressCity</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressCity" type="text" value='' /></td>
							<td>城市</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressDetail</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="addressDetail" type="text" value='' /></td>
							<td>具体地址</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">postcode</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="postcode" type="text" value='' /></td>
							<td>邮编</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">isDefault</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="isDefault" type="text" value='' /></td>
							<td>是否为用户默认收货地址，0：不是，1：是，不传默认为0</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="userAddress_updateDefaultAddr">用户设置/取消某条收货地址为默认</h2>
				<form action="<%=ctx%>/api/userAddress?type=updateDefaultAddr" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=updateDefaultAddr</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">isDefault</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="isDefault" type="text" value='' /></td>
							<td>是否为用户默认收货地址，0：不是，1：是，不传默认为0</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">addressId</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="addressId" type="text" value='' /></td>
							<td>收货地址ID</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="userAddress_getAll">获取用户全部收货地址</h2>
				<form action="<%=ctx%>/api/userAddress" method="get">
					<input type="hidden" name="type" value="getAll"/>
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=getAll</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
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
							<td>
								<span style="color: green;">成功返回：</span><br/>
								{"itemList":[{"id":45,"uid":"66013309","name":"贝主席","phoneNumber":"15997887687","addressProvince":"安徽省","addressCity":"滁州市","addressDetail":"某地","postcode":"210002","isDefault":1}],
								"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				
				<h2 id="userAddress_getById">获取用户某条收货地址详情</h2>
				<form action="<%=ctx%>/api/userAddress" method="get">
					<input type="hidden" name="type" value="getById"/>
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/userAddress?type=getById</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
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
							<td><font color="red">addressId</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="addressId" type="text" value='' /></td>
							<td>收货地址ID</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回：</span><br/>
								{"item":{"id":41,"uid":"111101074912","name":"代收人","phoneNumber":"025-40595464","addressProvince":"江苏省","addressCity":"南京市","addressDetail":"随手科技","postcode":"210000","isDefault":1},
								"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >失败返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				
				<h2 id="returnOrder">用户退款/退货</h2>
				<form action="<%=ctx%>/api/returnOrder" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/returnOrder</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">orderId</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="orderId" type="text" value='' /></td>
							<td>退货订单ID</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">isReceived</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="isReceived" type="text" value='' /></td>
							<td>是否收到货，0：未收到货  1：已收到货</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">reason</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="reason" type="text" value='' /></td>
							<td>退款/退货原因flag</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">refundAmount</font></td>
							<td>double</td>
							<td><input style="width: 200px" name="refundAmount" type="text" value='' /></td>
							<td>退款金额</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">remark</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="remark" type="text" value='' /></td>
							<td>其他说明</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">img1</font></td>
							<td>file</td>
							<td><input style="width: 200px" name="img1" type="file" value='' /></td>
							<td>照片凭证1</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">img2</font></td>
							<td>file</td>
							<td><input style="width: 200px" name="img2" type="file" value='' /></td>
							<td>照片凭证2</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">img3</font></td>
							<td>file</td>
							<td><input style="width: 200px" name="img3" type="file" value='' /></td>
							<td>照片凭证3</td>
						</tr>
						<tr>
							<td>opt</td>
							<td><font color="red">img4</font></td>
							<td>file</td>
							<td><input style="width: 200px" name="img4" type="file" value='' /></td>
							<td>照片凭证4</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">成功返回：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								
								<span style="color: red;">验证失败：</span><br/>
								{"status":{"code":2004,"desc":"Return order is null","showDesc":"退款订单加载失败"}}<br/>
								{"status":{"code":2005,"desc":"Return order status is unpaid or refunded","showDesc":"亲，订单未支付或已退款"}}<br/>
								{"status":{"code":2006,"desc":"Return money more than the order","showDesc":"亲，退款金额不能超过订单总额哦"}}<br/>
								{"status":{"code":2007,"desc":"Already reply to return","showDesc":"亲，您已申请过退款哦"}}<br/>
								{"status":{"code":2008,"desc":"Return money out of date","showDesc":"亲，退款时限已过了哦"}}<br/>
								
								<span style="color: red;">错误返回：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}
							</td>
						</tr>
					</table>
				</form>
				<p>&nbsp;</p>
				
				<h2 id="mauStats">用户月度活跃时长统计</h2>
				<form action="<%=ctx%>/api/mauStats" method="post">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/mauStats</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">stayMillis</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="stayMillis" type="text" value='' /></td>
							<td>用户在app内停留时间（毫秒数），最大不超过一天</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">执行成功：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >参数错误：</span><br/>{"status":{"code":1002,"desc":"Parameters Error","showDesc":""}}<br/>
								<span style="color: red;"  >Server出错：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}<br/>
								<span style="color: red;">禁止Get请求：</span><br/>{"status":{"code":1013,"desc":"Sign time out","showDesc":"请求方式非法"}}
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="feedback_get">查询反馈与回复</h2>
				<form action="<%=ctx%>/api/feedback" method="get">
					<table>
						<tr>
							<th>地址</th>
                            <th colspan="4"><%=ctx%>/api/feedback</th>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">type</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="type" type="text" value='' /></td>
							<td>反馈类型，1：订单问题；2：功能改进</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">cursorId</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="cursorId" type="text" value='' /></td>
							<td>第一次请求cursorId传''或不传，记录上一次检索到的id</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">执行成功：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >参数错误：</span><br/>{"status":{"code":1002,"desc":"Parameters Error","showDesc":""}}<br/>
								<span style="color: red;"  >Server出错：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}<br/>
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				<h2 id="feedback_post">发表意见反馈</h2>
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
                            <td><font color="red">sign</font></td>
                            <td>String</td>
                            <td><input style="width: 200px" name="sign" type="text" value='' /></td>
                            <td>sign</td>
                        </tr>
						<tr>
							<td>must</td>
							<td><font color="red">t</font></td>
							<td>long</td>
							<td><input style="width: 200px" name="t" type="text" value='<%=System.currentTimeMillis() %>' /></td>
							<td>时间戳</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">uid</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="uid" type="text" value='' /></td>
							<td>uid</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">type</font></td>
							<td>int</td>
							<td><input style="width: 200px" name="type" type="text" value='' /></td>
							<td>反馈类型，1：订单问题；2：功能改进</td>
						</tr>
						<tr>
							<td>must</td>
							<td><font color="red">content</font></td>
							<td>String</td>
							<td><input style="width: 200px" name="content" type="text" value='' /></td>
							<td>意见反馈内容，不超过500个字符</td>
						</tr>
						<tr>
							<td>--------</td>
							<td>--------</td>
							<td>--------</td>
							<td><input name="" type="submit" value="提交" /></td>
							<td>
								<span style="color: green;">执行成功：</span><br/>{"status":{"code":1000,"desc":"OK","showDesc":"成功"}}<br/>
								<span style="color: red;"  >参数错误：</span><br/>{"status":{"code":1002,"desc":"Parameters Error","showDesc":""}}<br/>
								<span style="color: red;"  >Server出错：</span><br/>{"status":{"code":1007,"desc":"Server Error","showDesc":""}}<br/>
							</td>
						</tr>
					</table>
				</form>	
				<p>&nbsp;</p>
				
				
				<!-- *************************************** API TEST FORM END ************************************************ -->
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
			<a href="javascript:window.open('http://www.suishou.cn')" >随手优惠</a><br/><br/>
		</div>
	</div>
	<!-- end gc-footer -->
	<!-- end gc-container -->
	<div style="width:30px;height:30px;position:fixed;bottom:0px;right:0px;background-color:#BBB;padding:8px 8px 0px 8px;"><center><a href="#" style="text-decoration: none;font-weight: bold;">TOP</a></center></div>
	</div>
</body>
</html>

