import React from "react";
import Context from "../Context";

let Ons = require('react-onsenui');

class BrowseGenresPanel
    extends React.Component {

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.List dataSource={this.context.genres}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                      onClick={() => this.props.navigator.pushPage({
                          id: 'genreComposers',
                          genreId: item.id
                      })}>
                          {item.name}
                      </Ons.ListItem>}/>
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        style={{flex: 'none'}}
                        className="backButton">
                Back
            </Ons.Button>
        </div>;
    }
}

BrowseGenresPanel.contextType = Context;
export default BrowseGenresPanel;