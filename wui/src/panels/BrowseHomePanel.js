import React from "react";

let Ons = require('react-onsenui');

class BrowseHomePanel
    extends React.Component {

    render() {
        let pages = [
            {
                name: 'By Composer',
                id: 'composers'
            },
            {
                name: 'By Period',
                id: 'eras'
            },
            {
                name: 'By Genre',
                id: 'genres'
            },
        ]

        return (
            <Ons.List dataSource={pages}
                      renderRow={(item) =>
                          <Ons.ListItem key={item.id} modifier="chevron"
                                        onClick={() => this.props.navigator.pushPage({id: item.id})}>
                              {item.name}
                          </Ons.ListItem>}/>
        );
    }
}

export default BrowseHomePanel;