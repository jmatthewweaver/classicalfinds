import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class BrowseEraComposersPanel
    extends React.Component {

    componentDidMount() {
        Ajax.getEraComposers(this.props.eraId)
            .then(resp => {
                this.context.updateEraComposers(this.props.eraId, resp.data);
            })
    }

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>
            <Ons.List dataSource={this.context.eraComposers[this.props.eraId]}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                                                         onClick={() =>
                                                             this.props.navigator.pushPage({
                                                                 id: 'composerWorks',
                                                                 composerId: item.id
                                                             })}>
                          {item.name}
                      </Ons.ListItem>}
            />
            <Ons.Button icon="fa-arrow-left" onClick={() => this.props.navigator.popPage()}
                        className="backButton" style={{flex: 'none'}}>
                Back
            </Ons.Button>

        </div>
    }
}

BrowseEraComposersPanel.contextType = Context;
export default BrowseEraComposersPanel;