import React from "react";
import Context from "../Context";

let Ons = require('react-onsenui');

class BrowseErasPanel
    extends React.Component {

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        style={{flex: 'none'}}
                        className="backButton">
                Back
            </Ons.Button>
            <Ons.List dataSource={this.context.eras}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                                                         onClick={() => this.props.navigator.pushPage({
                                                             id: 'eraComposers',
                                                             eraId: item.id
                                                         })}>
                          {item.name}
                      </Ons.ListItem>}/>
        </div>;
    }
}

BrowseErasPanel.contextType = Context;
export default BrowseErasPanel;