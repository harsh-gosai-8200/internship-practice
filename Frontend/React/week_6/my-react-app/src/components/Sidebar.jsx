import Link from "./Link";


function Sidebar(){

    const links = [
        {label : 'Dropdown', path : '/'},
        {label : 'Accordion', path : '/accordion'},
        {label : 'Buttons', path : '/buttons'},
        {label : 'Model', path : '/model'},
        {label : 'Table', path : '/table'},
        {label : 'Counter', path : '/counter'}
    ];

    const renderedlinks = links.map((link)=>{
        return <Link key={link.label} to={link.path} className='mb-3' activeClassName='font-bold border-l-4 border-blue-500 pl-2'>{link.label}</Link>
    })

    return (
        <div className="sticky tpo-0 overflow-y-scroll flex flex-col item-star">
            {renderedlinks}
        </div>
    )
}

export default Sidebar;