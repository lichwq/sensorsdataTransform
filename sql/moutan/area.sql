select
    a.id,
    a.areaCn,
    b.city_name_cn as cityCn,
    b.sheng_name_cn as shengCn
from
(
select
	get_json_object(json,'$._id') as id,
	get_json_object(json,'$.name') as areaCn,
	get_json_object(json,'$.cityEnglishName') as cityEn
from ods.dim_data
where
     (get_json_object(json,'$.type') = 'area2' or get_json_object(json,'$.type') = 'area3')
and get_json_object(json,'$._id') is not null 
and get_json_object(json,'$.name') is not null 
and get_json_object(json,'$.cityEnglishName') is not null
) a
left join
(
select
    city_name_en,
    city_name_cn,
    sheng_name_cn
from
    onetruehive.d_01_city
) b
on a.cityEn = b.city_name_en
union
select
    get_json_object(json,'$._id') as id,
    get_json_object(json,'$.name') as areaCn,
	get_json_object(json,'$.name') as cityCn,
	get_json_object(json,'$.sheng') as shengCn
from
    ods.dim_data
where
    get_json_object(json,'$.type')  = 'city'
