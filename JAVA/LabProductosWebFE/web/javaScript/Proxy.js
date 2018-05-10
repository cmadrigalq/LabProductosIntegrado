/**
 Cynthia Madrigal Quesada. 10:20pm
 Clase encargada de administrar las peticiones
 
 */

const jsonFromObject = (k, object) => {
    /*si no es un objeto lo devuelve sin hacer nada*/
    if ((!(object instanceof Object)) || object === null || object === undefined) {
        return object;
    }
    /**Sino, extrae los atributos del objeto y crea un objeto anonimo 
     con el _class correspondiente al className del objeto
     */
    return Object.keys(object)
            .reduce((json, atributo) => {
                json[atributo] = object[atributo];
                return json;
            }, {
                _class: object.constructor.name
            });
};


const objectFromJson = (k, json) => {
    if (json === null || json === undefined || json.class === undefined) {
        return json;
    }
    //crea un objeto dependiendo del atributo _class del json
    let _class = json.class.split(".");
    _class = _class[_class.length-1];
    return Object.keys(json)
            .reduce((obj, atributo) => {
                if (atributo != "class")
                    obj[atributo] = json[atributo];
                return obj;
            }, eval(`new ${_class}()`));
};

const toJson = object => {
    return JSON.stringify(object, jsonFromObject);
};
const toObject = (jsonText) => {
    return JSON.parse(jsonText,objectFromJson);
};
////método para transformar una serie de argumentos a parte del link para las peticiones
//// /servidor/action=accion&arg0="hola"&arg1="mundo"
const empaquetador = (args) => {
    return args.reduce(
            (resultado, argumento, indice) => {
        return resultado += `&arg${indice}=${toJson(argumento)}`
    }
    , ""/*estado inicial*/
            );
};

const Proxy = (servidor/*nombre del servlet*/,
        accion,
        metodo/*GET,POST,DELETE...*/,
        callBack,
        ...parametros) => {
    console.log("Action: " + accion);
    let AJAX_req = new XMLHttpRequest();
    let url = `${servidor}?action=${accion}`;
    AJAX_req.open(metodo, url, true);
    AJAX_req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    AJAX_req.onreadystatechange = function () {
        if (AJAX_req.readyState === 4 && AJAX_req.status === 200) {
            let jsonText = AJAX_req.responseText;
            console.log("Respuesta: " + jsonText);
            let objeto;
            try{
                objeto = toObject(jsonText);
            }catch(ex){
                objeto = ex;
            }
            callBack(objeto);
        }
    };
    /**envio de los parametros*/
    AJAX_req.send(
            empaquetador(parametros)
            );
};





