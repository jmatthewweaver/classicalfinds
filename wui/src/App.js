import React from 'react';
import './App.css';
import Context from './Context';
import Ajax from "./Ajax";
import GenreList from "./widgets/GenreList";

export default class App
extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            context: {
                genres: []
            }
        }
    }

    componentDidMount() {
        Ajax.getGenres().then(resp => {
            this.setState({
                context: {
                    ...this.state.context,
                    genres: resp.data
                }
            })
        });
    }

    render() {
        return (
            <Context.Provider value={this.state.context}>
                <GenreList />
            </Context.Provider>
        );
    }
}
