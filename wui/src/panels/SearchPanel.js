import React from "react";
import Ajax from "../Ajax";
import axios from "axios";
import Context from "../Context";
import BrowseComposerWorksPanel from "./BrowseComposerWorksPanel";
import WorkPanel from "./WorkPanel";

let Ons = require('react-onsenui');

class SearchPanel
    extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            query: '',
            searchResults: {}
        }
        this.cancelToken = undefined;
    }

    search(query) {
        this.setState({
            query
        });

        if(this.cancelToken) {
            this.cancelToken.cancel('Aborting previous search request');
            this.cancelToken = undefined;
        }

        this.cancelToken = axios.CancelToken.source();

        Ajax.search(query, this.cancelToken.token)
            .then(resp => this.setState({
                searchResults: resp.data || {}
            }));
    }

    render() {
        let composerResults = this.state.searchResults.composers || [];
        let workResults = this.state.searchResults.works || [];

        return <Ons.Page>
            <Ons.Navigator renderPage={(route, navigator) => {
                switch (route.id) {
                    case 'home':
                        return <Ons.Page key="browseHome">
                            <div style={{height: '100%', display: 'flex', flexFlow: 'column nowrap'}}>
                                <Ons.SearchInput style={{
                                    width: '100%',
                                    color: '#444',
                                    background: '#fff',
                                    borderRadius: 0,
                                    flex: 'none'
                                }}
                                                 value={this.state.query}
                                                 onChange={e => this.search(e.target.value)}/>
                                <div style={{flex: 1, overflow: 'auto'}}>
                                    {composerResults.length > 0 &&
                                    <Ons.List dataSource={composerResults}
                                              renderHeader={() => <Ons.ListHeader>Composers</Ons.ListHeader>}
                                              renderRow={item =>
                                                  <Ons.ListItem key={item.id} modifier="chevron"
                                                  onClick={() => navigator.pushPage({
                                                      id: 'composerWorks',
                                                      composerId: item.id
                                                  })}>
                                                      {item.name}
                                                  </Ons.ListItem>}/>
                                    }
                                    {workResults.length > 0 &&
                                    <Ons.List dataSource={workResults}
                                              renderHeader={() => <Ons.ListHeader>Works</Ons.ListHeader>}
                                              renderRow={item => {
                                                  let composer = this.context.composers.filter(c => c.id === item.composerId);
                                                  return <Ons.ListItem key={item.id} modifier="chevron"
                                                  onClick={() => navigator.pushPage({
                                                      id: 'work',
                                                      workId: item.id
                                                  })}>
                                                      {item.title}
                                                      {composer ?
                                                          ' by ' + composer[0].name : ''}
                                                  </Ons.ListItem>
                                              }}/>
                                    }
                                </div>
                            </div>
                        </Ons.Page>

                    case 'composerWorks':
                        return <Ons.Page key="searchComposerWorks">
                            <BrowseComposerWorksPanel composerId={route.composerId} navigator={navigator} />
                        </Ons.Page>

                    case 'work':
                        return <Ons.Page key="searchWork">
                            <WorkPanel workId={route.workId} navigator={navigator} />
                        </Ons.Page>
                }
            }}
                           initialRoute={{id: 'home'}}/>

        </Ons.Page>
    }
}

SearchPanel.contextType = Context;
export default SearchPanel;