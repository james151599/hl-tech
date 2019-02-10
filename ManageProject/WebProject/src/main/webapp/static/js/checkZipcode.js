/**
 * geolocation info
 */

/**
 * check input zipcode length
 */
var zipcodeFlag = false;
function checkZipcodeLength(country_iso,obj,msg) {
	zipcodeFlag = false;
	var countryLength = {'CAN' : [7],'DEU' : [4,5],'FRA' : [5],'GBR' : [6,7,8],'USA' : [5]};
    var countryIso = country_iso;
    var cmpData = countryLength[countryIso];
    var zipValue = $.trim(obj.value);
    obj.value = zipValue;
    if(typeof(cmpData) != "undefined") {
        cmpZipcodeLength(cmpData, zipValue.length);
	    if(!zipcodeFlag) {
	    	var str = '';
	    	for(var i=0;i<cmpData.length;i++) {
	    		str += cmpData[i];
	    		if(i != cmpData.length - 1) {
	    			str += ',';
	    		}
	    	}
	    	alert(msg + str);
	        obj.focus();
	        return false;
	    }
    }
    return true;
}

function cmpZipcodeLength(cmpData, val) {
	for(var i=0;i<cmpData.length;i++) {
		if(cmpData[i] == val) {
			zipcodeFlag = true;
		}
	}
}