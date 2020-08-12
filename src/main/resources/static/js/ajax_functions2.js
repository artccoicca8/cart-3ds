


	$('#enviroment').change(function() {
		 var cboText = $("#enviroment option:selected").text();
	      console.log("url values  " +cboText )
	       if (cboText == "LOCAL" ||cboText == "Multimarca" ) {
	    	   $("#acctNumber").val("374641000000009");
	    	   $("#accMerchantId").val("9876543210001");
     	   }else if (cboText == "Prod"){
				$("#key").val("09p0tV6I0Zu4ay2D.C9b5Qa3fsizTvkkV3UZdLCifjqjKHIgAzqW6vULFH0D83BNHpA7zW7xVu1xAB857");
    	  		$("#accBin").val("230358");
				$("#accMerchantId").val("12345678912003");
			}else {
    	  		$("#acctNumber").val("4110760000000008");
    	  		$("#accMerchantId").val("M00215");
    	  
      }
	     
	});
	

      function versioning(){
    	  
      document.getElementById("mensajeVersioning").innerHTML = "";
      var keyText = $('#key').val();
      var cboText = $("#enviroment option:selected").text();
      console.log("url values  " +cboText )
      	      
      var versioningDataJsonParse = "{}";
 
      var acctNumber = $("#acctNumber").val();
      var transbank = $("#transbank").is(":checked");
      
	  var urlEnviroment = ""; 
      if (cboText == "LOCAL" ||cboText == "Multimarca" ) {
    	  urlEnviroment = $("#enviroment").val() ;
      }else {
    	  urlEnviroment = $("#enviroment").val()+"versioning";
      }
      
      
       
      var method = "POST";
      var inputVersioning = "";
      
      
      console.log("key " + keyText) ;
    
      
      
      if(transbank){
		 // POST
        var versioningDataJson = {};
        versioningDataJson["acctNumber"] = acctNumber;
        versioningDataJson["acquirerMerchantID"] = $("#accMerchantId").val();
        versioningDataJson["transbank"] = transbank;
        versioningDataJson["urlEnviroment"] = urlEnviroment;
        versioningDataJson["keyText"] = keyText;
        versioningDataJson["method"] = "POST";
        
        
        versioningDataJsonParse = JSON.stringify(versioningDataJson);
        console.log("Antes de enviar el versioningDataJson : " +versioningDataJsonParse );
        inputVersioning = JSON.stringify(versioningDataJson, undefined, 2);
      } else { // GET
       
        urlEnviroment = urlEnviroment + "/" + accMerchantId + "/" + acctNumber;
        inputVersioning = urlEnviroment;
      }

      console.log("urlEnviroment " + urlEnviroment) ;
      document.getElementById("versioning").innerHTML = inputVersioning;
      
      var paramsAJAX = {
          type: "POST",
          url: "../cart3dspe/api/versioning",
          headers: getHeaderForAuthentication(),
          contentType: "application/json; charset=utf-8",
          crossDomain: true,
          dataType: "json",
          success: function (data, status, jqXHR) {
              var jsonString = JSON.stringify(data)
              var jsonConvertedData = JSON.parse(jsonString);

              if(jsonConvertedData["threeDSServerTransID"]!=null){
                  console.log("**** : "+jsonConvertedData["threeDSServerTransID"]);
              }


              // $('#primerMensaje').text(jsonString);
              document.getElementById("mensajeVersioning").innerHTML = JSON.stringify(data, undefined, 2);

              // trama parq
              var datosJsonParse =   retornaJsonPe() ;
              document.getElementById("parks").innerHTML = JSON.stringify(datosJsonParse, undefined, 2);

          },
          complete(response, status){
            if(status != 'success'){
              document.getElementById("mensajeVersioning").innerHTML = JSON.stringify(response.responseJSON, undefined, 2);
            }
          },
          error: function (jqXHR, status) {
              console.log(jqXHR);  
              console.log("error  "  ) ;

              // alert('fail ' + status.code);
          }
      }

      if(transbank){
        paramsAJAX["data"] = versioningDataJsonParse;
      }

      $.ajax(paramsAJAX);

    }

    function getHeaderForAuthentication(){
        var keyText = $('#key').val();
        console.log("key " + keyText) ;
        var transbank = $("#transbank").is(":checked");
        var results = {
          'Content-Type':'application/json'
        }
        if(transbank){
          results["key"] = keyText;
          results["ALG-API-VERSION"] = "1172945160";
        } else {
          results["Authorization"] = keyText;
        }

        return results;

    }

    function authentication(){

    	
     var cboText = $("#enviroment option:selected").text();
     console.log("url values  " +cboText )
     
      var urlEnviroment = ""; 
     if (cboText == "LOCAL" ||cboText == "Multimarca" ) {
    	  urlEnviroment = $("#enviroment").val() ;
    	  urlEnviroment = urlEnviroment.replace("threeDSVersioning","authentication")
      }else {
    	  urlEnviroment = $("#enviroment").val()+"authentication";
      }
      
      console.log("urlEnviroment AUTH PARQ " + urlEnviroment) ;
      
      // trasbn tipo c = 5449610000000004

      var message = $('#mensajeVersioning').text();
      var jsonConvertedData = JSON.parse(message);
      var threeDSServerTransIDsTring = jsonConvertedData["threeDSServerTransID"];
      console.log("threeDSServerTransIDsTring : "+threeDSServerTransIDsTring);

      // var datosJsonParse = retornaJsonPe() ;
      var authenticactionDataJsonParse = $("#parks").val();// JSON.stringify(datosJsonParse);
      console.log("Antes de enviar el authenticationDataJson : " +authenticactionDataJsonParse );
      // document.getElementById("$("#parks").val();//").innerHTML =
		// JSON.stringify(datosJsonParse, undefined, 2);


      var accMerchantId = $('#accMerchantId').val();
      var accMerchantId = '\"accMerchantId\"' ;
      console.log("accMerchantId " + accMerchantId) ;

      var headers = getHeaderForAuthentication();
      headers["threeDSServerTransID"] = threeDSServerTransIDsTring;
      headers["urlEnviroment"] = urlEnviroment;
      
        $.ajax({
            type: "POST",
            url: "../cart3dspe/api/authentication",
            headers:headers,
            data: authenticactionDataJsonParse,
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType: "json",
            success: function (data, status, jqXHR) {
                var jsonString = JSON.stringify(data)
                var jsonConvertedData = JSON.parse(jsonString);
                if(jsonConvertedData["transStatus"]=='C'){
                    $('#resultado').text("Existe Challenge");
                }else {
                    $('#resultado').text("Se ha autenticado de manera exitosa");
                }
                // $('#primerMensaje').text(jsonString);
                document.getElementById("primerMensaje").innerHTML = JSON.stringify(data, undefined, 2);


                // pgcq
                var message = $('#primerMensaje').text();
                var jsonConvertedData = JSON.parse(message);
                var pgcqData = {};
                pgcqData["threeDSServerTransID"] = jsonConvertedData["threeDSServerTransID"];
                pgcqData["acsTransID"] = jsonConvertedData["acsTransID"];
                pgcqData["messageType"] = "Pgcq";
                pgcqData["messageVersion"] = "2.1.0";
                pgcqData["challengeWindowSize"] = "05";
                console.log("Antes de enviar el pgcq" +JSON.stringify(pgcqData) );
                document.getElementById("segundoMensaje").innerHTML = JSON.stringify(pgcqData, undefined, 2);
            },
            complete(response, status){
              if(status != 'success'){
                $('#resultado').text("")
                document.getElementById("primerMensaje").innerHTML = JSON.stringify(response.responseJSON, undefined, 2);
              }
            },
            error: function (jqXHR, status) {
                console.log(jqXHR);
                // alert('fail ' + status.code);
            }
        });
    }

    function pgcq(){
    		
    	
    	
      var cboText = $("#enviroment option:selected").text();
       
    	     
      var urlEnviroment = ""; 
      if (cboText == "LOCAL" ||cboText == "Multimarca" ) {
      	  urlEnviroment = $("#enviroment").val() ;
     	  urlEnviroment = urlEnviroment.replace("threeDSVersioning","authentication")
       }else {
     	  urlEnviroment = $("#enviroment").val()+"authentication";
       }
 
     
        console.log("urlEnviroment AUTH PGCQ " + urlEnviroment) ;

        var headers = getHeaderForAuthentication();
        headers["urlEnviroment"] = urlEnviroment;
       
        $.ajax({
            type: "POST",
            url: "../cart3dspe/api/authenticationPgcq",
            headers: headers,
            data: $("#segundoMensaje").val(),
            contentType: "application/json; charset=utf-8",
            crossDomain: true,
            dataType:"text",
            success: function (data, status, jqXHR) {
                console.log("Exitoso");
                console.log(data);
                var myFrame = $("#acsFrame").contents().find('body');
                myFrame.html(data);
            },
            complete(response, status){
              if(status != 'success'){
                var myFrame = $("#acsFrame").contents().find('body');
                myFrame.html(response.responseText);
              }
            },
            error: function (jqXHR, status) {
                console.log("Que mierda paso "+ status);
                console.log(jqXHR);
                // alert('fail ' + status.code);
            }
        });

    }

    function retornaJsonPe(){



        var pgcqData = {};

            // pgcqData["threeDSServerTransID"] =
			// jsonConvertedData["threeDSServerTransID"];
            // pgcqData["acsTransID"] = jsonConvertedData["acsTransID"];

            var accMerchantId = $("#accMerchantId").val();
            var acctNumber = $("#acctNumber").val();
			var accBIN = $("#accBIN").val();

            pgcqData["messageType"] = "pArq";
            pgcqData["acctNumber"] = acctNumber;// "5449610000000004";
            pgcqData["cardExpiryDate"] = "2212";
            pgcqData["deviceChannel"] = "02";
            pgcqData["messageCategory"] = "01";
            pgcqData["messageVersion"] = "2.1.0";
            pgcqData["threeDSRequestorAuthenticationInd"] = "01";

            pgcqData["acquirerBIN"] = accBIN;
            pgcqData["acquirerMerchantID"] = accMerchantId;// "597026007992";
            pgcqData["cardholderName"] = "Challenge One";
            pgcqData["purchaseAmount"] = "100";
            pgcqData["purchaseCurrency"] = "840";
            pgcqData["purchaseExponent"] = "2";
            pgcqData["purchaseDate"] = "20190523061226";


            pgcqData["browserAcceptHeader"] = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
            pgcqData["browserIP"] = "1.12.123.255";
            pgcqData["browserJavaEnabled"] = "true";
            pgcqData["browserLanguage"] = "en";
            pgcqData["browserColorDepth"] = "32";
            pgcqData["browserScreenHeight"] = "1920";
            pgcqData["browserScreenWidth"] = "1080";

            pgcqData["browserTZ"] = "0";
            pgcqData["browserUserAgent"] = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0";

            var parsJsonPrro = JSON.stringify(pgcqData) ;


            console.log("parse : " + parsJsonPrro);


            return pgcqData ;

// console.log("Antes de enviar el pgcq" +JSON.stringify(pgcqData) );
    }
    
    function headerAutorizathionMethod() {
    	 var keyText = $('#key').val();
         console.log("key " + keyText) ;
         
         var urlEnviroment = $("#enviroment").val() + "authentication";
         var transbank = $("#transbank").is(":checked");
         var results = {
           'Content-Type':'application/json'
            
         }
         
         results["urlEnviroment"] = urlEnviroment;
         if(transbank){
           results["key"] = keyText;
           results["ALG-API-VERSION"] = "1172945160";
          
         } else {
           results["Authorization"] = keyText;
         }

         return results;
	}
    
    