import React from "react";
import Context from "../Context";
import Ajax from "../Ajax";

let Ons = require('react-onsenui');

class BrowseFormComposersPanel
    extends React.Component {

    componentDidMount() {
        Ajax.getFormComposers(this.props.formId)
            .then(resp => {
                this.context.updateFormComposers(this.props.formId, resp.data);
            })
    }

    render() {
        return <div style={{
            height: '100%',
            display: 'flex',
            flexFlow: 'column nowrap'
        }}>

            <Ons.List dataSource={this.context.formComposers[this.props.formId]}
                      style={{flex: 1, overflow: 'auto'}}
                      renderRow={(item) => <Ons.ListItem key={item.id} modifier="chevron"
                                                         onClick={() =>
                                                             this.props.navigator.pushPage({
                                                                 id: 'formComposerWorks',
                                                                 formId: this.props.formId,
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

BrowseFormComposersPanel.contextType = Context;
export default BrowseFormComposersPanel;