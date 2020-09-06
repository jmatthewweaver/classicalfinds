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

    static getGenreComposers(genreId) {
        return axios.request({
            url: Ajax.buildUrl(['genres', genreId, 'composers']),
            method: 'GET'
        })
    }

    static getGenreComposerWorks(genreId, composerId) {
        return axios.request({
            url: Ajax.buildUrl(['genres', genreId, 'composers', composerId, "works"]),
            method: 'GET'
        })
    }

    static getWorkVideos(workId) {
        return axios.request({
            url: Ajax.buildUrl(['works', workId, 'videos']),
            method: 'GET'
        })
    }
}