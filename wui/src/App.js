import React from 'react';
import Context from './Context';
import Ajax from "./Ajax";
import BrowsePanel from "./panels/BrowsePanel";
import ViewVideoPanel from "./panels/ViewVideoPanel";

let Ons = require('react-onsenui');

export default class App
    extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tabIndex: 0,
            context: {
                genres: [],
                genreComposers: {},
                updateGenreComposers: (genreId, composers) => {
                    let newComposers = {};
                    Object.assign(newComposers, this.state.context.genreComposers);
                    newComposers[genreId] = composers;
                    this.setState({
                        context: {
                            ...this.state.context,
                            genreComposers: newComposers
                        }
                    })
                },
                genreComposerWorks: {},
                updateGenreComposerWorks: (genreId, composerId, works) => {
                    let newWorks = {};
                    Object.assign(newWorks, this.state.context.genreComposerWorks);
                    newWorks[genreId + '_' + composerId] = works;
                    this.setState({
                        context: {
                            ...this.state.context,
                            genreComposerWorks: newWorks
                        }
                    })
                },
                workVideos: {},
                updateWorkVideos: (workId, workVideos) => {
                    let newWorkVideos = {};
                    Object.assign(newWorkVideos, this.state.context.workVideos);
                    newWorkVideos[workId] = workVideos;
                    this.setState({
                        context: {
                            ...this.state.context,
                            workVideos: newWorkVideos
                        }
                    })
                },
                activeWork: null,
                updateActiveWork: (work) => {
                    this.setState({
                        context: {
                            ...this.state.context,
                            activeWork: work
                        }
                    })
                },
                eras: [],
                eraComposers: {},
                updateEraComposers: (eraId, composers) => {
                    let newComposers = {};
                    Object.assign(newComposers, this.state.context.eraComposers);
                    newComposers[eraId] = composers;
                    this.setState({
                        context: {
                            ...this.state.context,
                            eraComposers: newComposers
                        }
                    })
                },
                forms: [],
                formComposers: {},
                updateFormComposers: (formId, composers) => {
                    let newComposers = { };
                    Object.assign(newComposers, this.state.context.formComposers);
                    newComposers[formId] = composers;
                    this.setState({
                        context: {
                            ...this.state.context,
                            formComposers: newComposers
                        }
                    })
                },
                formComposerWorks: {},
                updateFormComposerWorks: (formId, composerId, works) => {
                    let newWorks = {};
                    Object.assign(newWorks, this.state.context.formComposerWorks);
                    newWorks[formId + '_' + composerId] = works;
                    this.setState({
                        context: {
                            ...this.state.context,
                            formComposerWorks: newWorks
                        }
                    })
                },
                composers: [],
                composerWorks: {},
                updateComposerWorks: (composerId, works) => {
                    let newWorks = {};
                    Object.assign(newWorks, this.state.context.composerWorks);
                    newWorks[composerId] = works;
                    this.setState({
                        context: {
                            ...this.state.context,
                            composerWorks: newWorks
                        }
                    })
                }
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
        Ajax.getEras().then(resp => {
            this.setState({
                context: {
                    ...this.state.context,
                    eras: resp.data
                }
            })
        });
        Ajax.getComposers().then(resp => {
            this.setState({
                context: {
                    ...this.state.context,
                    composers: resp.data
                }
            })
        });
        Ajax.getForms().then(resp => {
            this.setState({
                context: {
                    ...this.state.context,
                    forms: resp.data
                }
            })
        })
    }

    render() {
        return (
            <Context.Provider value={this.state.context}>
                <Ons.Page>
                    <div style={{width: '100%', height: '100%', display: 'flex', flexFlow: 'column nowrap'}}>
                        <div style={{flex: 1}} className="viewVideoPanel">
                            <ViewVideoPanel />
                        </div>
                        <div style={{flex: 1, position: 'relative'}}>
                            <Ons.Tabbar index={this.state.tabIndex}
                                        onPostChange={e => this.setState({tabIndex: e.activeIndex})}
                                        position="bottom"
                                        renderTabs={() => [
                                            {
                                                content: <BrowsePanel key="browsePage" />,
                                                tab: <Ons.Tab key="browseTab" label="Browse" icon="fa-list" />
                                            }
                                        ]}>

                            </Ons.Tabbar>
                        </div>
                    </div>
                </Ons.Page>
            </Context.Provider>
        );
    }
}
