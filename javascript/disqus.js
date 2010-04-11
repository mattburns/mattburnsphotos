var disqus_href = window.location.href;
var disqus_date = new Date();
var disqus_hashIndex = disqus_href.indexOf("#");
if (disqus_hashIndex != -1) {
	disqus_href=disqus_href.substring(0,disqus_hashIndex);
}
var DsqCache = {};
var disqus_script = document.createElement("script");
var disqus_shortname, disqus_domain;
var disqus_url, disqus_title, disqus_message, disqus_sort, disqus_dev;
var disqus_container_id, disqus_category_id, disqus_developer;
var disqus_identifier, disqus_frame_theme;
var disqus_extra_qs = "";
var disqus_shortname_re = [
		/https?:\/\/(www\.)?disqus\.com\/forums\/([\w_\-]+)/i,
		/https?:\/\/(www\.)?([\w_\-]+)\.disqus\.com/i,
		/https?:\/\/(www\.)?dev\.disqus\.org\/forums\/([\w_\-]+)/i ];
var disqus_browser = {
	ie : /msie/i.test(navigator.userAgent)
			&& !/opera/i.test(navigator.userAgent),
	ie6 : (!window.XMLHttpRequest) ? true : false,
	ie7 : (document.all && !window.opera && window.XMLHttpRequest) ? true
			: false
};
var disqus_detect_shortname = function() {
	var d = function(g) {
		var h = (g.getAttribute) ? g.getAttribute("src") : g.src;
		if (h) {
			for ( var f = 0; f < disqus_shortname_re.length; f++) {
				var e = h.match(disqus_shortname_re[f]);
				if (e && e.length && e.length == 3) {
					return e[2]
				}
			}
		}
		return null
	};
	var a = document.getElementsByTagName("script");
	for ( var c = a.length - 1; c >= 0; c--) {
		var b = d(a[c]);
		if (b !== null) {
			return b
		}
	}
	return null
};
if (typeof (disqus_url) == "undefined" || disqus_url === "") {
	disqus_url = disqus_href
}
if (typeof (disqus_title) == "undefined") {
	disqus_title = ""
}
if (typeof (disqus_message) == "undefined") {
	disqus_message = ""
} else {
	var disqus_isUTF8 = false;
	if (/msie/i.test(navigator.userAgent)
			&& !/opera/i.test(navigator.userAgent)) {
		for ( var i = 0; i < disqus_message.length; i++) {
			if (disqus_message.charCodeAt(i) > 256) {
				disqus_isUTF8 = true;
				break
			}
		}
	}
	if (disqus_isUTF8) {
		disqus_message = ""
	} else {
		if (disqus_message.length > 400) {
			disqus_message = disqus_message.substring(0, disqus_message
					.indexOf(" ", 350))
		}
	}
}
if (typeof (disqus_sort) == "undefined") {
	disqus_sort = ""
}
if (typeof (disqus_container_id) == "undefined") {
	disqus_container_id = "disqus_thread"
}
if (typeof (disqus_category_id) == "undefined") {
	disqus_category_id = ""
}
if (typeof (disqus_developer) == "undefined") {
	disqus_developer = ""
}
if (typeof (disqus_identifier) == "undefined") {
	disqus_identifier = ""
}
if (typeof (disqus_require_moderation_s) != "undefined") {
	disqus_extra_qs += "&require_mod_s="
			+ encodeURIComponent(disqus_require_moderation_s)
}
if (typeof (disqus_remote_auth_s2) != "undefined") {
	disqus_extra_qs += "&remote_auth_s2="
			+ encodeURIComponent(disqus_remote_auth_s2)
}
if (typeof (disqus_per_page) != "undefined") {
	disqus_extra_qs += "&per_page=" + encodeURIComponent(disqus_per_page)
}
if (typeof (disqus_author_s2) != "undefined") {
	disqus_extra_qs += "&author_s2=" + encodeURIComponent(disqus_author_s2)
}
if (typeof (disqus_shortname) == "undefined") {
	//disqus_shortname = disqus_detect_shortname()
	disqus_shortname = 'mattburns';
}
if (typeof (disqus_domain) == "undefined") {
	disqus_domain = disqus_dev ? "dev.disqus.org" : "disqus.com"
}
var disqus_head = document.getElementsByTagName("head")[0];
if (!disqus_head) {
	disqus_head = document.getElementById("disqus_thread")
}
var disqus_styles = document.createElement("style");
disqus_head.appendChild(disqus_styles);
DsqCache.inlineStylesheet = disqus_styles.sheet;
if (!DsqCache.inlineStylesheet) {
	DsqCache.inlineStylesheet = document.styleSheets[document.styleSheets.length - 1]
}

var disqus_stylesheet = document.createElement("link");
disqus_stylesheet.rel = "stylesheet";
disqus_stylesheet.type = "text/css";
disqus_stylesheet.href = "http://" + disqus_domain + "/forums/"
		+ disqus_shortname + "/styles.css";
if (disqus_browser.ie6 || disqus_browser.ie7) {
	disqus_stylesheet.href += "?b=" + (disqus_browser.ie6 ? "ie6" : "ie7")
}
disqus_head.appendChild(disqus_stylesheet);
disqus_script.type = "text/javascript";
disqus_script.src = "http://" + disqus_shortname + "." + disqus_domain
		+ "/thread.js?url=" + encodeURIComponent(disqus_url) + "&message="
		+ encodeURIComponent(disqus_message) + "&title="
		+ encodeURIComponent(disqus_title) + "&sort="
		+ encodeURIComponent(disqus_sort) + "&category_id="
		+ encodeURIComponent(disqus_category_id) + "&developer="
		+ ((disqus_developer == "0" || !disqus_developer) ? 0 : 1)
		+ "&identifier=" + encodeURIComponent(disqus_identifier)
		+ disqus_extra_qs + "&" + disqus_date.getTime();
//alert(disqus_script.src);
disqus_script.charset = "UTF-8";
var dsq_loading_problem_message = function() {
	var a = document.getElementById("dsq-content-stub");
	a.innerHTML = '<a href="#" onclick="dsq_reload_loader(); return false;">Click here to reload the comments</a>. This website is using Disqus to power its comments.'
};
var dsq_reload_loader = function() {
	var b = document.getElementById(disqus_container_id);
	var a = document.createElement("script");
	if (!disqus_shortname) {
		document.location.reload()
	}
	if (a != null && b != null) {
		b.innerHTML = "";
		a.type = "text/javascript";
		a.src = disqus_script.src = "../../javascript/disqus.js";
		(document.getElementsByTagName("head")[0] || document
				.getElementsByTagName("body")[0]).appendChild(a)
	}
};
var disqus_stub = document.createElement("div");
disqus_stub.id = "dsq-content-stub";
disqus_stub.innerHTML = 'Loading comments...<div style="height:15px;width:300px;border:solid 1px #999;background-color:#fff;margin:10px 0;"><div style="height:15px;width:15px;background-color:#d4e4ff;" id="dsq-content-progress">&nbsp;</div></div><div style="font-size:8pt;display:none;" id="dsq-loading-problem"><a href="#" onclick="dsq_loading_problem_message(); return false;">Problem loading comments?</a></div>';
var disqus_dataContainer = document.createElement("div");
disqus_dataContainer.id = "dsq-content";
disqus_dataContainer.style.display = "none";
disqus_dataContainer.appendChild(disqus_script);
document.getElementById(disqus_container_id).appendChild(disqus_dataContainer);
document.getElementById(disqus_container_id).appendChild(disqus_stub);
window.setTimeout(function() {
	if (disqus_stub.style.display != "none") {
		document.getElementById("dsq-loading-problem").style.display = "block"
	}
}, 10000);
var DsqLoader = function() {

	this.initInterval = null;
	this.addLoadEvent = function(a) {
		var b = window.onload;
		if (typeof window.onload != "function") {
			window.onload = a
		} else {
			window.onload = function() {
				if (b) {
					b()
				}
				a()
			}
		}
	};
	this.init = function() {
		if (typeof Dsq == "undefined") {
			if (!DsqLoader.initInterval) {
				DsqLoader.initInterval = window
						.setInterval(DsqLoader.init, 500)
			}
			return
		}
		if (DsqLoader.initInterval) {
			window.clearInterval(DsqLoader.initInterval)
		}
		if (disqus_browser.ie && disqus_frame_theme !== "cnn2") {
			var b = document.getElementById(disqus_container_id);
			var d = b.getElementsByTagName("iframe");
			var a = document.getElementById("dsq-content");
			if (a) {
				for ( var c = 0; c < d.length; c++) {
					d[c].style.width = a.offsetWidth
				}
			}
		}
	};
	this.addLoadEvent(this.init)
};
DsqLoader = new DsqLoader();