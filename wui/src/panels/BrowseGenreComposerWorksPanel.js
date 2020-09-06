import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class BrowseGenreComposerWorksPanel
    extends React.Component {

    componentDidMount() {
        Ajax.getGenreComposerWorks(this.props.genreId, this.props.composerId)
            .then(resp => {
                this.context.updateGenreComposerWorks(this.props.genreId, this.props.composerId, resp.data);
            })
    }

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        className="backButton" style={{flex: 'none'}}>
                Back
            </Ons.Button>

            <Ons.List dataSource={this.context.genreComposerWorks[this.props.genreId + '_' + this.props.composerId]}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                      onClick={() => {
                          console.log('workId =', item.id);
                          this.props.navigator.pushPage({
                              id: 'work',
                              workId: item.id
                          })
                      }}>
                          {item.title}
                      </Ons.ListItem>}
            />
        </div>
    }
}

BrowseGenreComposerWorksPanel.contextType = Context;
export default BrowseGenreComposerWorksPanel;