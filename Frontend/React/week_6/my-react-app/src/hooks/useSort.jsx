import { useState } from "react";


function useSort({data, config}){

    const [sortOrder, setSortOrder] = useState(null);
    const [sortBy, setSortBy] = useState(null);

    const handleClick = (label) => {
        if(sortBy && label!==sortBy){
            setSortOrder('asc');
            setSortBy(label);
            return;
        }

        if (sortOrder === null) {
            setSortOrder('asc');
            setSortBy(label);
        } else if (sortOrder === 'asc') {
            setSortOrder('desc');
            setSortBy(label)
        } else if (sortOrder === 'desc') {
            setSortOrder(null);
            setSortBy(null);
        }
    }

    let sortedData = data;
    if (sortOrder && sortBy) {
        const { sortValue } = config.find(column => column.label === sortBy);
        sortedData = [...data].sort((a, b) => {
            const value1 = sortValue(a);
            const value2 = sortValue(b);

            const reverseOrder = sortOrder === 'asc' ? 1 : -1;

            if (typeof value1 === 'string') {
                return value1.localeCompare(value2) * reverseOrder;
            } else {
                return (value1 - value2) * reverseOrder;
            }
        });
    }

    return {
        sortOrder,
        sortBy,
        sortedData,
        handleClick,
    };

}

export default useSort;