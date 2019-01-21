import React from 'react';
import GraphiQL from 'graphiql';
// import fetch from 'isomorphic-fetch';
import * as css from '../assets/graphiql.css'

function graphQLFetcher(graphQLParams) {
    return fetch(window.location.origin + '/graphql', {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(graphQLParams),
    }).then(response => response.json());
}

const GraphQlView = () => {
    return <div style={{height: '500px'}}><GraphiQL fetcher={graphQLFetcher} /></div>;
};

export default GraphQlView;