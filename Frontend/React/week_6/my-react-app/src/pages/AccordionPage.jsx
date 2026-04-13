import Accordion from "../components/Accordion";


function AccordionPage(){
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

  return <Accordion items={items} />;
}

export default AccordionPage;