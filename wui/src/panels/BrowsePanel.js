import React from "react";
import BrowseGenresPanel from "./BrowseGenresPanel";
import BrowseHomePanel from "./BrowseHomePanel";
import BrowseGenreComposersPanel from "./BrowseGenreComposersPanel";
import BrowseGenreComposerWorksPanel from "./BrowseGenreComposerWorksPanel";
import WorkPanel from "./WorkPanel";

let Ons = require('react-onsenui');

class BrowsePanel
    extends React.Component {

    render() {
        return <Ons.Page>
            <Ons.Navigator renderPage={(route, navigator) => {
                switch(route.id) {
                    case 'home':
                        return <Ons.Page key="browseHome">
                            <BrowseHomePanel navigator={navigator} />
                        </Ons.Page>

                    case 'genres':
                        return <Ons.Page key="browseGenres">
                            <BrowseGenresPanel navigator={navigator} />
                        </Ons.Page>

                    case 'genreComposers':
                        return <Ons.Page key="browseGenreComposers">
                            <BrowseGenreComposersPanel genreId={route.genreId} navigator={navigator} />
                        </Ons.Page>

                    case 'genreComposerWorks':
                        return <Ons.Page key="browseGenreComposerWorks">
                            <BrowseGenreComposerWorksPanel genreId={route.genreId} composerId={route.composerId} navigator={navigator} />
                        </Ons.Page>

                    case 'work':
                        return <Ons.Page key="browseWork">
                            <WorkPanel workId={route.workId} navigator={navigator} />
                        </Ons.Page>

                    default:
                        return undefined;
                }
            }}
            initialRoute={{id: 'home'}} />
            <BrowseGenresPanel />
        </Ons.Page>
    }
}

export default BrowsePanel;