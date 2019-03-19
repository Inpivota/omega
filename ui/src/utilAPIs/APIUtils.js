export const handleErrors = (response) => {
    if (response.ok) return response.json();
    else throw Error(response.statusText)
};

export const getForcastData = () => {
    const query = `query {
        productsForForecasting {
            description,
            finaleId,
            getBuildOutToDays,
            name,
            getProductAmazonInventory,
            getProductShippedInventory,
        }
    }`;
    return fetch("/graphql", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({query}),
    }).then(response => handleErrors(response))
    
};