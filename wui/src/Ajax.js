import Config from "./Config";
import axios from "axios";

export default class Ajax {

    static buildUrl(urlParts) {
        let parts = [Config.apiUrl].concat(urlParts);
        return parts.join('/');
    }

    static getGenres() {
        return axios.request({
            url: Ajax.buildUrl(['genres']),
            method: 'GET'
        })
    }
}