import React from "react";
import Context from "../Context";

let Ons = require('react-onsenui');

class GenreList
    extends React.Component {

    constructor(props, context) {
        super(props, context);
    }

    render() {
        return <Ons.List dataSource={this.context.genres}
                         renderRow={(item) => <Ons.ListItem key={item.id}>
                             {item.name}
                         </Ons.ListItem>}/>;
    }
}

GenreList.contextType = Context;
export default GenreList;