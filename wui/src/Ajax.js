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

    static getEras() {
        return axios.request({
            url: Ajax.buildUrl(['eras']),
            method: 'GET'
        })
    }

    static getEraComposers(eraId) {
        return axios.request({
            url: Ajax.buildUrl(['eras', eraId, 'composers']),
            method: 'GET'
        })
    }

    static getComposers() {
        return axios.request({
            url: Ajax.buildUrl(['composers']),
            method: 'GET'
        })
    }

    static getComposerWorks(composerId) {
        return axios.request({
            url: Ajax.buildUrl(['composers', composerId, 'works']),
            method: 'GET'
        })
    }

    static getForms() {
        return axios.request({
            url: Ajax.buildUrl(['forms']),
            method: 'GET'
        })
    }

    static getFormComposers(formId) {
        return axios.request({
            url: Ajax.buildUrl(['forms', formId, 'composers']),
            method: 'GET'
        })
    }

    static getFormComposerWorks(formId, composerId) {
        return axios.request({
            url: Ajax.buildUrl(['forms', formId, 'composers', composerId, 'works']),
            method: 'GET'
        })
    }

}