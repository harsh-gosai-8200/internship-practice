const items = [
    {
      id : 'khfdg',
      label : 'can i use react on a project?',
      content : 'yes you can..'
    },
    {
      id : 'jdsfk',
      label : 'can i use javaScript on a project?',
      content : 'yes you can..'
    },
    {
      id : 'fhgff',
      label : 'can i use rust on a project?',
      content : 'yes you can..'
    }
  ];

function myFunction(item, expandedIndex){
    return items.map((item, index) => {
        if(index===expandedIndex){
            return 'Expanded!'
        }else{
            return 'collapsed';
        }
    });
}

myFunction(propsItem, 0);
myFunction(propsItem, 2);