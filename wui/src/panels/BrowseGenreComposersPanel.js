import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class BrowseGenreComposersPanel
    extends React.Component {

    componentDidMount() {
        Ajax.getGenreComposers(this.props.genreId)
            .then(resp => {
                this.context.updateGenreComposers(this.props.genreId, resp.data);
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

            <Ons.List dataSource={this.context.genreComposers[this.props.genreId]}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                                                         onClick={() =>
                                                             this.props.navigator.pushPage({
                                                                 id: 'genreComposerWorks',
                                                                 genreId: this.props.genreId,
                                                                 composerId: item.id
                                                             })}>
                          {item.name}
                      </Ons.ListItem>}
            />
        </div>
    }
}

BrowseGenreComposersPanel.contextType = Context;
export default BrowseGenreComposersPanel;