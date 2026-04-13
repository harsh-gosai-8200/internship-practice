import Model from "../components/Model";
import Button from "../components/Button";
import { useState } from "react";


function ModelPage(){
    const [showModel, setShowModel] = useState(false);

    const handelClick = () => {
        setShowModel(true);
    }

    const handelClose = () => {
        setShowModel(false);
    }

    const actionBar = <div>
        <Button primary onClick={handelClose}>I Agree</Button>
    </div>

    const model = <Model onClose={handelClose}  actionBar={actionBar}>  
        <p>
            Here is important aggriment...
        </p>
    </Model>;

    return (
    <div className="relative">
        <Button onClick={handelClick} primary> Open Model </Button>
        {showModel && model}
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio aspernatur obcaecati magnam, saepe a ipsum architecto unde blanditiis rem, debitis consequuntur, quidem facilis dolores sed animi. Assumenda id quasi natus?
        Facilis commodi modi enim quam earum neque eum architecto, voluptatibus, facere possimus sunt nulla. Nobis, iste, aliquam atque accusantium sed, earum nostrum dolore consequuntur quia iure dolorum quisquam repellat vitae.
        Magnam consectetur illum quis. Atque iste maiores modi ut pariatur! Repellat veniam fuga et, ad praesentium quidem, in, dolore ea cupiditate non expedita delectus deserunt quo ducimus optio esse nihil.
        Nobis magnam repellat temporibus cumque? Numquam et assumenda accusamus modi quibusdam obcaecati odio vel illo. Non, pariatur doloribus, rerum aspernatur obcaecati quam repellendus excepturi quo possimus alias tempora, fugit deserunt?
        Pariatur odio atque minus soluta saepe quas, autem repellendus impedit dolorem ad culpa quis ipsam labore optio nisi voluptatibus laudantium inventore vero sint. Labore voluptates qui, officiis dolore nulla adipisci!
        Saepe aliquid officia nihil quidem dolores voluptas quo magnam maiores ducimus totam quibusdam labore omnis eos, itaque in voluptate officiis nisi consequatur aut excepturi numquam quos possimus necessitatibus! Laudantium, neque!
        Quisquam, delectus similique? Accusamus laudantium labore ullam quibusdam corporis dolores id soluta sunt enim ad ex dolorem nihil nesciunt, iste dignissimos praesentium voluptatum, numquam aperiam. Illo ea commodi minima quisquam.
        In error numquam illo neque recusandae nemo, fugiat natus impedit doloribus? Nulla quas eum animi distinctio magni, enim velit ut aliquam? Veritatis est doloremque aperiam repellendus neque pariatur, ex perspiciatis.
        Et, magnam! Aperiam, commodi amet! Ex nesciunt ab quisquam, voluptates corrupti aperiam quibusdam voluptatem cumque nostrum aspernatur, earum illum culpa! Fugiat illum libero quasi reprehenderit atque porro sint unde aut.
        Aut dolores ipsam atque nemo asperiores dolorum hic nulla illo enim, voluptatum harum expedita optio numquam autem amet laudantium esse omnis vero. Error consequuntur magnam, veniam id reiciendis eveniet beatae.
        Optio quas dolorum rerum libero nulla nobis facilis aliquam! Nulla officia possimus odio nobis officiis eius. Alias libero temporibus quia, dolores in, ratione recusandae maiores non eaque repellendus, laboriosam quos.
        Alias, quasi voluptatum facilis laudantium pariatur error suscipit optio quis dolorem non explicabo. Deserunt, facere dolor nulla nisi quos officia est ipsam voluptatum praesentium hic necessitatibus consequatur eaque ducimus velit!
        Provident eos neque et ad odit ratione deserunt officiis dignissimos nobis. Cumque eius deleniti non molestias facere molestiae! Quibusdam fuga quidem suscipit ipsa illum vitae unde exercitationem dolores voluptatum possimus.
        Eum enim tempore quos nam, nemo fuga dolores sapiente vel laudantium? Nobis laboriosam accusamus ea pariatur sequi praesentium ad voluptatum illum magni illo fugiat culpa suscipit recusandae vel, quasi quas.
        Possimus mollitia eligendi optio cumque quisquam, fugit perspiciatis eum dignissimos aut, molestiae expedita sunt nulla numquam sint illum sed blanditiis incidunt nisi. Commodi quia molestias natus necessitatibus, sequi error illo!
        Beatae animi facilis quod, optio laudantium labore nisi excepturi deleniti. Tenetur, quibusdam facilis nulla enim vel ea cum itaque sunt impedit, inventore distinctio? Cupiditate dolorum alias vitae maxime cumque est.
        Doloribus dolorum illum blanditiis perferendis dignissimos rem a sit delectus odio repellat, explicabo, ducimus consequatur. Deleniti quasi, delectus aut distinctio voluptatum commodi tempore molestiae facilis optio reprehenderit aspernatur impedit voluptatem.
        Sed impedit magni possimus nobis dolore pariatur eum aperiam qui quasi eos quod voluptas, fugiat cupiditate iure minus corrupti itaque sit, officia expedita doloremque. Maxime sequi ab dolorum earum praesentium.
        Repellat iure, officia enim debitis at atque aliquam sint, repellendus ratione culpa reprehenderit eum consectetur autem eveniet quae delectus laudantium voluptates quam? Dolore quaerat quidem tempore repellat iste minus sequi.
        Atque repellendus enim et cumque impedit sapiente consectetur commodi, ea fugiat hic. Repellat recusandae ab error at impedit nesciunt voluptatibus modi, odio accusamus, optio mollitia dolore sequi, atque ipsa eius.
        Suscipit quae dicta minima sapiente, quam dolore eos nam praesentium provident expedita, error totam, ipsa ratione illo? Quia officiis dolorem laudantium, doloribus aut obcaecati mollitia iusto illo voluptates rem reiciendis?
        Obcaecati, id? Tempore dolores laudantium distinctio ut labore, velit asperiores error quae, deserunt doloremque consequatur exercitationem expedita cum? Ullam hic tempore excepturi dolorem obcaecati optio odio beatae accusamus aspernatur! Odit.
        Totam expedita placeat eius vero quisquam libero numquam ut. Tempore laboriosam similique maiores cum excepturi? Laborum, saepe. Optio harum cumque aspernatur culpa deserunt. Ea culpa eveniet maiores labore dicta voluptatum.
        Voluptas architecto perferendis reiciendis sequi sapiente veniam sunt laboriosam ad ut qui totam error expedita consectetur placeat quae repudiandae, ea temporibus, eligendi eveniet dolorum quod quo. Voluptatum, vel. Quibusdam, est!
        Eos nemo maiores eligendi illum cupiditate alias, culpa ipsum ea exercitationem perspiciatis nobis soluta sequi id a ut tenetur autem dolorum placeat sint dolore delectus aspernatur mollitia veritatis minima. Eaque.
        Laboriosam perspiciatis cupiditate rem iste ipsum. Molestias vel vero et magnam quisquam quasi doloribus ex, officia earum cumque magni iusto nemo in amet ut rem minus eius? Magnam, cumque dolorem?
        Officia sapiente provident magnam assumenda labore. Excepturi, quasi. Quos vel quod molestias tempore, laudantium repudiandae porro a modi ipsa expedita corrupti, nesciunt cupiditate eaque libero aliquid laborum. Explicabo, temporibus. Voluptatem.
        Cum dolore, dicta ducimus beatae dolor id debitis molestiae? Doloribus, alias in hic ut commodi numquam, excepturi porro iste obcaecati facilis perferendis ad expedita pariatur ex perspiciatis, consequuntur inventore voluptatum.
        Libero blanditiis ipsa aperiam! Porro et enim temporibus cum facere laborum sunt. Eum omnis facilis excepturi fugiat dolor impedit ratione eveniet ullam velit iste, distinctio quae repellat ducimus consequatur similique!
        Ullam ipsam, magni a quo accusantium aut corrupti asperiores quisquam voluptas voluptatum impedit, id neque eaque eum consequuntur modi voluptate vel vero? Non error magnam placeat. Nesciunt perspiciatis quibusdam nobis.
        Consequatur porro cumque labore earum inventore sit voluptate ex sequi molestiae quasi provident ullam dignissimos atque quis blanditiis explicabo officiis nemo doloribus deserunt, dicta suscipit neque quidem tempore distinctio! Nam!
        Voluptate repudiandae enim ipsum commodi quos, itaque ex aliquid illum vero velit quasi nostrum culpa corrupti praesentium quibusdam cupiditate dicta dolorem earum eveniet animi quod atque dolores ea! Cumque, ducimus?
        Voluptate excepturi deleniti similique dicta ducimus nihil fuga perspiciatis et, veritatis mollitia non doloribus ea, odio, vero architecto itaque error iste beatae distinctio ipsa minima maxime impedit. Dolor, quam nihil.
        Illo odio ipsam nisi sint vero est eum alias optio necessitatibus veritatis maxime ut doloribus similique nulla voluptas voluptatum id debitis voluptates sequi earum, consequatur quod ullam. Repudiandae, consectetur fugiat!
        Fuga omnis quibusdam tenetur ipsa sunt doloribus nemo eveniet, voluptatum cumque nostrum libero autem sequi aspernatur dolorum ea nobis ex. Inventore repellat pariatur tempore ad esse excepturi veniam quis laborum?
        Nobis deleniti fuga autem quasi recusandae quaerat quibusdam, eligendi voluptatibus, beatae voluptatem optio voluptate voluptatum quas quam harum consequuntur praesentium provident enim corrupti ea assumenda iste. Eum nostrum explicabo aliquam!
        Inventore natus facere itaque dolorem aut sequi vitae obcaecati magni optio provident. Iste, excepturi itaque iusto, veritatis laborum soluta quod rerum similique enim, et reprehenderit pariatur labore praesentium sunt vel?
        Odit repellendus voluptates similique placeat id nam explicabo magni praesentium doloribus magnam corporis accusamus, obcaecati optio voluptatum eos totam assumenda et autem voluptatibus beatae quidem ut. Rerum ullam nesciunt amet?
        Quidem, quisquam nisi quae sunt placeat fuga molestias ad ipsum cum, nulla ducimus nesciunt aperiam porro inventore explicabo dignissimos exercitationem, temporibus eveniet sint iste provident sequi voluptatibus quod magnam! Corporis.
        Vero corrupti natus blanditiis quaerat placeat numquam animi ipsum ducimus neque consequatur dolorem fuga rem repellat ullam temporibus aliquid odio aut laudantium, exercitationem veritatis quasi, commodi fugiat. Facere, iusto atque.
        Blanditiis, consectetur quibusdam. Iusto officia iure numquam. Vitae, velit alias? Ipsa, aliquid corporis aut enim soluta esse quibusdam officia suscipit sit dolores, possimus doloremque nemo, porro aspernatur vel architecto doloribus.
        Aut sed ex quam atque consequatur sequi ipsum consequuntur rem voluptates nisi quaerat veritatis quisquam facilis minus iusto harum laboriosam eaque voluptate, laborum delectus nihil natus temporibus? Error, nobis quam.
        Et nam, modi repellat omnis optio praesentium? Eum dolorem dolore placeat enim at nihil sequi officiis asperiores consectetur est! Rerum dolores dicta ducimus officia aliquam nostrum nam quos? Tenetur, inventore.
        Nam illo rerum necessitatibus iure optio cumque, saepe architecto fuga, esse quaerat officiis porro a eligendi! Ex eius aperiam sint soluta earum ducimus velit, tenetur, modi itaque vitae, corporis reiciendis.
        Sed, adipisci praesentium. Recusandae quisquam doloribus enim ut? Molestiae nobis recusandae temporibus vel ullam eveniet, dignissimos natus odit! Nesciunt asperiores velit magnam a commodi dolorum non, temporibus minus illum ratione?
        Atque, velit vero illo maiores saepe perferendis animi, adipisci ipsam eum explicabo nisi deserunt ratione labore, pariatur a dolorem laboriosam alias sapiente quam reiciendis. Ex sed sapiente ratione hic dolorum.
        Sed, quaerat! Quis numquam mollitia at, necessitatibus provident fugiat animi, obcaecati aliquid, aut iste temporibus unde optio voluptatibus! Explicabo deserunt doloribus veritatis impedit quibusdam tenetur veniam exercitationem molestiae beatae expedita.
        Quos est minus quam, eos ipsa sed a possimus quis expedita ipsum totam, animi qui similique, adipisci nemo veniam! Quam suscipit cupiditate inventore expedita ipsum vitae tenetur voluptates similique corrupti?
        Natus in eaque ratione aperiam, beatae quae vel dicta obcaecati omnis. Fugit voluptatum nihil qui ipsam cum? Dolorem quod laboriosam corrupti maiores necessitatibus velit? Asperiores ea fugit reprehenderit! Nam, explicabo?
        Similique dolor iure, ipsum consectetur natus, id soluta dolorum, tenetur a ut quae vitae quod. Molestias aspernatur quod expedita harum. Facilis, quibusdam vel dicta dignissimos quidem reprehenderit quae deserunt eveniet.</p>
        
        
    </div>
    );
}

export default ModelPage;